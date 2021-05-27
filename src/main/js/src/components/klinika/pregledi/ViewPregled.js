import React, { Component } from "react"
import ReceiptsService from "../../../services/ReceiptsService";
import PreglediService from "../../../services/PreglediService";

class ViewPregledComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            pregled: {},
            recepti: [],
            dijagnoza: '',
            opis: '',
            recept: ''
        }
        this.changeDijagnozaHandler = this.changeDijagnozaHandler.bind(this);
        this.changeOpisHandler = this.changeOpisHandler.bind(this);
        this.changeReceptHandler = this.changeReceptHandler.bind(this);
        this.addReciept = this.addReciept.bind(this);
    }

    componentDidMount() {
        PreglediService.getPregled(this.state.id).then(response => {

            this.setState({
                pregled: response.data,
                dijagnoza: response.data.dijagnoza,
                opis: response.data.opis,
                recepti: response.data.recepti
            });
        }).catch(err => {
            return;
        })

        ReceiptsService.getReceipts().then(response => {
            var recepti = response.data;
            var filtriraniRecepti = [];
            var i = 0;
            for (i; i < recepti.length; i++) {
                if (recepti[i].idPregleda == this.state.pregled.idPregleda) {
                    filtriraniRecepti.push(recepti[i]);
                }
            }
            this.setState({
                recepti: filtriraniRecepti
            });
        });

    }

    updatePregled(zavrsen) {

        var pregled = {}
        if (!zavrsen) {
            pregled = {
                dijagnoza: this.state.dijagnoza,
                opis: this.state.opis,
                pocetakTermina: this.state.pregled.pocetakTermina,
                cena: this.state.pregled.cena,
                popust: this.state.pregled.popust,
                idZdravstvenogKartona: this.state.pregled.idZdravstvenogKartona,
                idKlinike: this.state.pregled.idKlinike
            }
        }
        else {
            pregled = {
                dijagnoza: this.state.dijagnoza,
                opis: this.state.opis,
                pocetakTermina: this.state.pregled.pocetakTermina,
                krajTermina: new Date().toISOString().substring(0,19),
                cena: this.state.pregled.cena,
                popust: this.state.pregled.popust,
                idZdravstvenogKartona: this.state.pregled.idZdravstvenogKartona,
                idKlinike: this.state.pregled.idKlinike
            }
        }
        console.log(pregled);

        PreglediService.editPregled(this.state.id, pregled).then(response => {
            this.props.history.push(`/${this.state.pregled.idKorisnika}/radniKalendar/${this.state.pregled.idKlinike}`)
        });

    }

    changeDijagnozaHandler = (event) => {
        this.setState({ dijagnoza: event.target.value });
    }

    changeOpisHandler = (event) => {
        this.setState({ opis: event.target.value });
    }

    changeReceptHandler = (event) => {
        this.setState({ recept: event.target.value });
    }

    addReciept() {
        if (this.state.recept.length != 0) {

            const recept = {
                idPregleda: this.state.id,
                opisRecepta: this.state.recept,
                overen: false
            }

            ReceiptsService.createReceipt(recept).then(response => {
                this.state.recepti.push(response.data);
                this.setState({
                    recept:''
                })
            });

        }
        else {
            alert('Unesite recept.')
            return
        }
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
                                <h3 style={{ margin: "10px", textDecoration: "underline" }} className="text-center" > Detaljnije o pregledu </h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Opis pregleda: </label>
                                            <textarea readOnly placeholder="Opis pregleda" name="opis" className="form-control"
                                                value={this.state.opis} onChange={this.changeOpisHandler} />
                                            <br />
                                            <label> Dijagnoza: </label>
                                            <textarea readOnly placeholder="Dijagnoza" name="dijagnoza" className="form-control"
                                                value={this.state.dijagnoza} onChange={this.changeDijagnozaHandler} />
                                            <br />

                                            <div className="row" style={{ margin: '10px' }}>
                                                <label style={{ width: '100%' }}> Recepti: </label>
                                                <table className="table table-striped table bordered">
                                                    <div style={{ height: '200px', overflow: 'auto', backgroundColor: 'rgba(0, 123, 255,0.1)' }}>
                                                        <tbody>

                                                            {
                                                                this.state.recepti != null ?
                                                                    this.state.recepti.map(
                                                                        recept =>
                                                                            <tr id="lista" key={recept.idRecepta}>
                                                                                <td style={{ width: '10%' }}>
                                                                                    <textarea style={{ width: '100%' }} readOnly>{recept.opisRecepta}</textarea>
                                                                                </td>
                                                                            </tr>
                                                                    ) : (
                                                                        <tr id="lista">
                                                                            <td>
                                                                                <h3>Nema recepata</h3>
                                                                            </td>
                                                                        </tr>
                                                                    )
                                                            }

                                                        </tbody>
                                                    </div>
                                                </table>
                                            </div>
                                            <br />


                                        </div>

                                        <button disabled type="button" className="btn btn-info" onClick={() => this.updatePregled(true)}>Zavrsi pregled</button>
                                        <button disabled type="button" className="btn btn-success" onClick={() => this.updatePregled(false)}>Azuriraj</button>
                                        <button className="btn" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Nazad</button>
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
                                <h3 style={{ margin: "10px", textDecoration: "underline" }} className="text-center" > Azuriranje pregleda </h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Opis pregleda: </label>
                                            <textarea placeholder="Opis pregleda" name="opis" className="form-control"
                                                value={this.state.opis} onChange={this.changeOpisHandler} />
                                            <br />
                                            <label> Dijagnoza: </label>
                                            <textarea placeholder="Dijagnoza" name="dijagnoza" className="form-control"
                                                value={this.state.dijagnoza} onChange={this.changeDijagnozaHandler} />
                                            <br />

                                            <div className="row" style={{ margin: '10px' }}>
                                                <label style={{ width: '100%' }}> Recepti: </label>
                                                <table className="table table-striped table bordered">
                                                    <div style={{ height: '200px', overflow: 'auto', backgroundColor: 'rgba(0, 123, 255,0.1)' }}>
                                                        <tbody>

                                                            {
                                                                this.state.recepti != null ?
                                                                    this.state.recepti.map(
                                                                        recept =>
                                                                            <tr id="lista" key={recept.idRecepta}>
                                                                                <td style={{ width: '10%' }}>
                                                                                    <textarea style={{ width: '100%' }} readOnly>{recept.opisRecepta}</textarea>
                                                                                </td>
                                                                            </tr>
                                                                    ) : (
                                                                        <tr id="lista">
                                                                            <td>
                                                                                <h3>Nema recepata</h3>
                                                                            </td>
                                                                        </tr>
                                                                    )
                                                            }

                                                        </tbody>
                                                    </div>
                                                </table>
                                                <textarea placeholder="Recept" name="recept" className="form-control"
                                                    value={this.state.recept} onChange={this.changeReceptHandler} />
                                                <div style={{ textAlign: 'right' }}>
                                                    <button type='button' style={{ padding: '10px', fontSize: '10px' }} className="btn btn-success" onClick={this.addReciept}>Dodaj recept</button>
                                                </div>
                                            </div>
                                            <br />


                                        </div>

                                        <button type="button" className="btn btn-info" onClick={() => this.updatePregled(true)}>Zavrsi pregled</button>
                                        <button type="button" className="btn btn-success" onClick={() => this.updatePregled(false)}>Azuriraj</button>
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

export default ViewPregledComponent;