import React, { Component } from 'react';
import ClinicsService from '../../services/ClinicsService';
import '../../App.css'

class ListClinicsComponent extends Component {

	constructor(props) {
		super(props)

		this.state = {
			clinics: []
		}
		this.viewClinic = this.viewClinic.bind(this);
		this.addClinic = this.addClinic.bind(this);
		this.editClinic = this.editClinic.bind(this);
		this.deleteClinic = this.deleteClinic.bind(this);
	}

	viewClinic(id) {
		this.props.history.push(`/klinike/pregled/${id}`);
    }

	addClinic() {
		this.props.history.push('/klinike/dodaj');
	}

	editClinic(id) {
		this.props.history.push(`/klinike/izmeni/${id}`);
	}

	deleteClinic(id) {
		ClinicsService.deleteClinic(id).then((response) => {
			this.setState({ clinics: this.state.clinics.filter(clinic => clinic.idKlinike !== id)});
		});
	}

	componentDidMount() {

		ClinicsService.getClinics().then((response) => {
			this.setState({ clinics: response.data });
		});

	}

	render() {

		return (
			
			<div style={{ margin: "100px" }}>
				<h2 className="text-center">Lista klinika</h2>	
				<div className="row">

					<table className="table table-striped table bordered">

						<thead>
							<tr>
								<th> Naziv klinike</th>
								<th> Opis klinike</th>
								<th> Adresa klinike</th>
								<th></th>
							</tr>
						</thead>

						<tbody>
							{
								this.state.clinics.map(
									clinic =>
										<tr id="lista" key={clinic.idKlinike}>
											<td>{clinic.naziv}</td>
											<td>{clinic.opis}</td>
											<td>{clinic.adresa}</td>
											<td>
												<button onClick={() => this.viewClinic(clinic.idKlinike)} className="btn btn-info">Pregled</button>
												<button style={{ marginLeft: "10px" }} onClick={() => this.editClinic(clinic.idKlinike)} className="btn btn-light">Izmeni</button>
												<button style={{ marginLeft: "10px" }} onClick={() => this.deleteClinic(clinic.idKlinike)} className="btn btn-danger">Obrisi</button>
											</td>
										</tr>
									)
							}
						</tbody>
					</table>
				</div>
				<div className="row">
					<button className="btn btn-primary" onClick={this.addClinic}> Dodaj kliniku</button>
				</div>
			</div>
			
			)

	}
}

export default ListClinicsComponent