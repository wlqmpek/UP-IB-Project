import React, {Component} from "react"
import ClinicsService from "../../services/ClinicsService";

class UpdateClinicComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            naziv: '',
            opis: '',
            adresa: ''
        }
        this.changeAdresaHandler = this.changeAdresaHandler.bind(this);
        this.changeOpisHandler = this.changeOpisHandler.bind(this);
        this.changeNazivHandler = this.changeNazivHandler.bind(this);
        this.updateClinic = this.updateClinic.bind(this);
    }

    componentDidMount() {
        ClinicsService.getClinicById(this.state.id).then(response => {
            const clinic = response.data;

            this.setState({
                naziv: clinic.naziv,
                opis: clinic.opis,
                adresa: clinic.adresa
            });
        });
    }

    updateClinic = (event) => {
        event.preventDefault();

        const klinika = {
            naziv: this.state.naziv,
            opis: this.state.opis,
            adresa: this.state.adresa,
        }
        console.log(klinika);

        ClinicsService.updateClinic(klinika, this.state.id).then(response => {
            this.props.history.push("/klinike");
        });

    }

    changeNazivHandler = (event) => {
        this.setState({naziv: event.target.value});
    }

    changeOpisHandler = (event) => {
        this.setState({opis: event.target.value});
    }

    changeAdresaHandler = (event) => {
        this.setState({adresa: event.target.value});
    }

    cancel() {
        this.props.history.push("/klinike")
    }

    render() {
        return (
            <div style={{margin: "50px"}}>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 style={{margin: "10px", textDecoration: "underline"}} className="text-center"> Izmena
                                klinike</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> Naziv: </label>
                                        <input placeholder="Naziv" name="naziv" className="form-control"
                                               value={this.state.naziv} onChange={this.changeNazivHandler}/>
                                        <br/>
                                        <label> Opis: </label>
                                        <input placeholder="Opis" name="opis" className="form-control"
                                               value={this.state.opis} onChange={this.changeOpisHandler}/>
                                        <br/>
                                        <label> Adresa: </label>
                                        <input placeholder="Adresa" name="adresa" className="form-control"
                                               value={this.state.adresa} onChange={this.changeAdresaHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.updateClinic}>Izmeni</button>
                                    <button className="btn btn-default" onClick={this.cancel.bind(this)}
                                            style={{marginLeft: "10px"}}>Odustani
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

}

export default UpdateClinicComponent;
