import React, { Component } from 'react';
import AdminService from '../../services/AdminService';
import '../../App.css';

class ListAdminsComponent extends Component {

	constructor(props) {
		super(props)

		this.state = {
			admins: []
		}
		this.addAdmin = this.addAdmin.bind(this);
		this.editAdmin = this.editAdmin.bind(this);
		this.deleteAdmin = this.deleteAdmin.bind(this);
	}


	componentDidMount() {

		AdminService.getAdmini().then((response) => {
			this.setState({ admins: response.data.filter(admin => admin.vrstaAdministratora === "KLINICKOG_CENTRA") });
		});

	}


	addAdmin() {
		this.props.history.push(`/admini/dodaj`);
	}

	editAdmin(id) {
		this.props.history.push(`/admini/izmeni/${id}`);
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
                    <h3 style={{ margin: "10px", textDecoration: "underline" }} className="text-center"> Lista administratora kliničkog centra </h3>
                    <div className="card-body">
                        <button className="btn btn-primary" onClick={() => this.addAdmin()}>Dodaj admina</button>

                        {this.state.admins.length > 0 ? (
                            <div></div>
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
                                                    <td style={{ width: '10%' }}>
                                                        <button style={{ marginLeft: "10px" }} onClick={() => this.editAdmin(admin.idKorisnika)} className="btn btn-default">Izmeni</button>
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

export default ListAdminsComponent;