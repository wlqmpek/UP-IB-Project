import React,{ Component } from "react"
import ClinicsService from "../../services/ClinicsService";

class CreateClinicComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            naziv: '',
            opis: '',
            adresa: '',
            showAdresa: 'false'

        }
        this.changeAdresaHandler = this.changeAdresaHandler.bind(this);
        this.changeOpisHandler = this.changeOpisHandler.bind(this);
        this.changeNazivHandler = this.changeNazivHandler.bind(this);
        this.saveClinic = this.saveClinic.bind(this);
        this.setAdresa = this.setAdresa.bind(this);
    }

    changeNazivHandler = (event) => {
        this.setState({ naziv: event.target.value });
    }

    changeOpisHandler = (event) => {
        this.setState({ opis: event.target.value });
    }

    changeAdresaHandler = (event) => {
        this.setState({ adresa: event.target.value });
    }



    saveClinic = (event) => {
        event.preventDefault();

        const klinika = {
            naziv: this.state.naziv,
            opis: this.state.opis,
            adresa: this.state.adresa,
        }
        console.log(klinika);

        ClinicsService.createClinic(klinika).then(response => {
            this.props.history.push("/klinike");
        });

    }

    setAdresa()  {

        this.setState({
            showAdresa: 'true'
        })
    }

    cancel() {
        this.props.history.push("/klinike")
    }

    render() {
        return (
            <div style={{ margin: "50px" }}>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 style={{ margin: "10px", textDecoration: "underline" }} className="text-center">Dodaj kliniku</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> Naziv: </label>
                                        <input placeholder="Naziv" name="naziv" className="form-control"
                                            value={this.state.naziv} onChange={this.changeNazivHandler} />
                                        <br />
                                        <label> Opis: </label>
                                        <input placeholder="Opis" name="opis" className="form-control"
                                            value={this.state.opis} onChange={this.changeOpisHandler} />
                                        <br />
                                        <label> Adresa: </label>
                                        <input placeholder="Adresa" name="adresa" className="form-control"
                                            value={this.state.adresa} onChange={this.changeAdresaHandler} />
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveClinic}>Kreiraj</button>
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

export default CreateClinicComponent;
