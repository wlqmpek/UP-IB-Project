import React, { Component } from "react"
import ReceiptsService from "../../../services/ReceiptsService";
import PreglediService from "../../../services/PreglediService";
import { PacientService } from "../../../services/PacientService";
import ZdravstveniKartonService from "../../../services/ZdravstveniKartonService";

class UpdateZKComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            pregled: {},
            pacijent: {},
            izvestaj: '',
            visina: '',
            tezina: '',
            krvnaGrupa: '',
            dioptrija: '',
            alergije: ''
        }
        this.changeIzvestajHandler = this.changeIzvestajHandler.bind(this);
        this.changeVisinaHandler = this.changeVisinaHandler.bind(this);
        this.changeTezinaHandler = this.changeTezinaHandler.bind(this);
        this.changeKrvnaGrupaHandler = this.changeKrvnaGrupaHandler.bind(this);
        this.changeDioptrijaHandler = this.changeDioptrijaHandler.bind(this);
        this.changeAlergijeHandler = this.changeAlergijeHandler.bind(this);
        this.updateZK = this.updateZK.bind(this);
    }

    componentDidMount() {
        PreglediService.getPregledi().then(response => {
            this.setState({
                pregled: response.data.find(pregled =>
                    pregled.idZdravstvenogKartona == this.state.id
                )
            })

            ZdravstveniKartonService.getZdravstveniKarton(this.state.id).then(response => {
                this.setState({
                    izvestaj: this.state.pregled.opis,
                    visina: response.data.visina,
                    tezina: response.data.tezina,
                    krvnaGrupa: response.data.krvnaGrupa,
                    dioptrija: response.data.dioptrija,
                    alergije: response.data.alergije
                });
            }).catch(err => {
                return;
            })

        }).catch(err => {
            return;
        })

        PacientService.getPacients().then(response => {
            this.setState({
                pacijent: response.data.find(pacijent =>
                    pacijent.idZdravstvenogKartona == this.state.id
                )
            });
        });
        

    }

    updateZK() {
        // verifikacija podataka
        if (!this.state.izvestaj || !this.state.visina || !this.state.tezina || !this.state.krvnaGrupa
            || !this.state.dioptrija || !this.state.alergije) {
            alert('Sva polja su obavezna.')
            return
        }

        if (isNaN(this.state.visina)) {
            alert('Visina mora sadrzati samo cifre.')
            return
        }
        if (isNaN(this.state.tezina)) {
            alert('Tezina mora sadrzati samo cifre.')
            return
        }
        if (isNaN(this.state.dioptrija)) {
            alert('Dioptrija mora sadrzati samo cifre.')
            return
        }

        const ZK = {
            visina: this.state.visina,
            tezina: this.state.tezina,
            krvnaGrupa: this.state.krvnaGrupa,
            dioptrija: this.state.dioptrija,
            alergije: this.state.alergije,
        }

        this.state.pregled.opis = this.state.izvestaj
        PreglediService.editPregled(this.state.pregled.idPregleda, this.state.pregled);

        ZdravstveniKartonService.editZdravstveniKarton(this.state.id, ZK).then(response => {
            this.props.history.push(`/${this.state.pregled.idKorisnika}/radniKalendar/${this.state.pregled.idKlinike}`)
        });

        

    }

    changeIzvestajHandler = (event) => {
        this.setState({ izvestaj: event.target.value });
    }

    changeVisinaHandler = (event) => {
        this.setState({ visina: event.target.value });
    }

    changeTezinaHandler = (event) => {
        this.setState({ tezina: event.target.value });
    }

    changeKrvnaGrupaHandler = (event) => {
        this.setState({ krvnaGrupa: event.target.value });
    }

    changeDioptrijaHandler = (event) => {
        this.setState({ dioptrija: event.target.value });
    }
    changeAlergijeHandler = (event) => {
        this.setState({ alergije: event.target.value });
    }

    cancel() {
        this.props.history.push(`/${this.state.pregled.idKorisnika}/radniKalendar/${this.state.pregled.idKlinike}`)
    }

    render() {
        if (this.state.pregled.krajTermina !== null) {
            return (
                <div style={{ margin: "50px" }}>
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 style={{ margin: "30px", textDecoration: "underline" }} className="text-center" > Zdravstveni karton </h3>
                                <div className="text-center" style={{ color:'white' , backgroundColor: 'skyblue', borderRadius:'5%' , width:'30%' , margin:'auto'}}>
                                    Korisnik:&nbsp; {this.state.pacijent.ime + ' ' + this.state.pacijent.prezime}
                                </div>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Izvestaj za pregled: </label>
                                            <textarea placeholder="Izvestaj" name="izvestaj" className="form-control"
                                                value={this.state.izvestaj} onChange={this.changeIzvestajHandler} />
                                            <br />
                                            <label> Visina: </label>
                                            <input readOnly placeholder="Visina" name="visina" className="form-control"
                                                value={this.state.visina} onChange={this.changeVisinaHandler} />
                                            <br />
                                            <label> Tezina: </label>
                                            <input readOnly placeholder="Tezina" name="tezina" className="form-control"
                                                value={this.state.tezina} onChange={this.changeTezinaHandler} />
                                            <br />
                                            <label> Krvna grupa: </label>
                                            <input readOnly placeholder="Krvna grupa" name="krvnaGrupa" className="form-control"
                                                value={this.state.krvnaGrupa} onChange={this.changeKrvnaGrupaHandler} />
                                            <br />
                                            <label> Dioptrija: </label>
                                            <input readOnly placeholder="Dioptrija" name="dioptrija" className="form-control"
                                                value={this.state.dioptrija} onChange={this.changeDioptrijaHandler} />
                                            <br />
                                            <label> Alergije: </label>
                                            <textarea readOnly placeholder="Alergije" name="alergije" className="form-control"
                                                value={this.state.alergije} onChange={this.changeAlergijeHandler} />
                                            <br />

                                        </div>

                                        <button type="button" className="btn btn-success" onClick={() => this.updateZK()}>Azuriraj</button>
                                        <button className="btn" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Odustani</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
        else {
            return (
                <div style={{ margin: "50px" }}>
                    <div className="container">
                        <div className="row">
                            <div className="card offset-md-3">
                                <h3 style={{ margin: "30px", textDecoration: "underline" }} className="text-center" > Azuriranje zdravstvenog kartona </h3>
                                <div className="text-center" style={{ color: 'white', backgroundColor: 'skyblue', borderRadius: '5%', width: '30%', margin: 'auto' }}>
                                    Korisnik:&nbsp; {this.state.pacijent.ime + ' ' + this.state.pacijent.prezime}
                                </div>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Izvestaj za pregled: </label>
                                            <textarea placeholder="Izvestaj" name="izvestaj" className="form-control"
                                                value={this.state.izvestaj} onChange={this.changeIzvestajHandler} />
                                            <br />
                                            <label> Visina: </label>
                                            <input placeholder="Visina" name="visina" className="form-control"
                                                value={this.state.visina} onChange={this.changeVisinaHandler} />
                                            <br />
                                            <label> Tezina: </label>
                                            <input placeholder="Tezina" name="tezina" className="form-control"
                                                value={this.state.tezina} onChange={this.changeTezinaHandler} />
                                            <br />
                                            <label> Krvna grupa: </label>
                                            <input placeholder="Krvna grupa" name="krvnaGrupa" className="form-control"
                                                value={this.state.krvnaGrupa} onChange={this.changeKrvnaGrupaHandler} />
                                            <br />
                                            <label> Dioptrija: </label>
                                            <input placeholder="Dioptrija" name="dioptrija" className="form-control"
                                                value={this.state.dioptrija} onChange={this.changeDioptrijaHandler} />
                                            <br />
                                            <label> Alergije: </label>
                                            <textarea placeholder="Alergije" name="alergije" className="form-control"
                                                value={this.state.alergije} onChange={this.changeAlergijeHandler} />
                                            <br />

                                        </div>

                                        <button type="button" className="btn btn-success" onClick={this.updateZK}>Azuriraj</button>
                                        <button className="btn" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Odustani</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
    }

}

export default UpdateZKComponent;