import React, { Component } from "react"
import ClinicsService from "../../services/ClinicsService";
import AdminService from "../../services/AdminService";

class ViewClinicComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            clinic: {},
            admins: []
        }
    }

    componentDidMount() {
        ClinicsService.getClinicById(this.state.id).then(response => {
            this.setState({clinic: response.data});
        });
        AdminService.getAdmini().then((response) => {
            this.setState({ admins: response.data.filter(admin => admin.idKlinike === this.state.clinic.idKlinike) });
        });
    }

    addAdmin (id) {
        this.props.history.push(`/klinike/${id}/dodajAdmina`);
    }

    editAdmin(idKlinike,idAdmina) {
        this.props.history.push(`/klinike/${idKlinike}/izmeniAdmina/${idAdmina}`);
    }

    deleteAdmin(id) {
        AdminService.deleteAdmin(id).then((response) => {
            this.setState({ admins: this.state.admins.filter(admin => admin.idKorisnika !== id) });
        });
    }

    render() {
        return (
            <div style={{ margin: "50px 0" }}>
                <div className="card col-md-6 offset-md-3">
                    <h3 style={{ margin:"10px",textDecoration: "underline" }} className="text-center"> Pregled klinike </h3>
                    <div className="card-body">
                        <div style={{ 'margin-left': '25px' }}>
                            <div className="row">
                                <label> Naziv klinike:&emsp; </label>
                                <a href=""><div> {this.state.clinic.naziv} </div></a>
                            </div>
                            <br />
                            <div className="row">
                                <label> Opis klinike:&emsp; </label>
                                <a href=""><div> {this.state.clinic.opis} </div></a>
                            </div>
                            <br />
                            <div className="row">
                                <label> Adresa klinike:&emsp; </label>
                                <a href=""><div> {this.state.clinic.adresa} </div></a>
                            </div>
                            
                        </div>
                        <br />
                        <button className="btn btn-primary" onClick={() => this.addAdmin(this.state.clinic.idKlinike)}>Dodaj admina</button>

                        {this.state.admins.length > 0 ? (
                            <h3 style={{ margin: "10px" }} className="text-center">Admini</h3>
                        ) : (
                                <h3 style={{ margin: "10px" }} className="text-center">Ne postoje admini</h3>
                            )}

                        <div className="row">
                            <table className="table table-striped table bordered">
                                <thead>
                                    <tr>
                                        <th> Ime </th>
                                        <th> Prezime </th>
                                        <th> Email </th>
                                        <th> Vrsta admina </th>
                                        <th></th>
                                    </tr>
                                </thead>

                                <tbody>
                                    {
                                        this.state.admins.map(
                                            admin =>
                                                <tr id="lista" key={admin.idKorisnika}>
                                                    <td>{admin.imeKorisnika}</td>
                                                    <td>{admin.prezimeKorisnika}</td>
                                                    <td>{admin.emailKorisnika}</td>
                                                    <td>{admin.vrstaAdministratora}</td>
                                                    <td style={{ width:'10%' }}>
                                                        <button style={{ marginLeft: "10px" }} onClick={() => this.editAdmin(this.state.id,admin.idKorisnika)} className="btn btn-default">Izmeni</button>
                                                        <button style={{ marginLeft: "10px" }} onClick={() => this.deleteAdmin(admin.idKorisnika)} className="btn btn-danger">Obrisi</button>
                                                    </td>
                                                </tr>
                                        )
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewClinicComponent