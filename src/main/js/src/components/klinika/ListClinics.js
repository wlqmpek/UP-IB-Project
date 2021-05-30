import React, { Component } from 'react';
import ClinicsService from '../../services/ClinicsService';
import '../../App.css'
import {AuthenticationService} from '../../services/AuthenticationService'

class ListClinicsComponent extends Component {

	constructor(props) {
		super(props)

		this.state = {
			clinics: [],
			userRole: "",
			sortType: 'Rastuce',
			refreshState: ''
		}
		this.viewClinic = this.viewClinic.bind(this);
		this.addClinic = this.addClinic.bind(this);
		this.editClinic = this.editClinic.bind(this);
		this.deleteClinic = this.deleteClinic.bind(this);
		this.sort = this.sort.bind(this);
		this.changeSortTypeHandler = this.changeSortTypeHandler.bind(this);
	}

	changeSortTypeHandler = (event) => {
		this.setState({
			sortType: event.target.value
		});
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

	sort(param) {

		if (param === "naziv") {
			this.state.clinics.sort(function (a,b) {
				var x = a.naziv.toLowerCase();
				var y = b.naziv.toLowerCase();
				if (x < y) { return -1; }
				if (x > y) { return 1; }
				return 0;
			});
		} else if (param === "adresa") {
			this.state.clinics.sort(function (a, b) {
				var x = a.adresa.toLowerCase();
				var y = b.adresa.toLowerCase();
				if (x < y) { return -1; }
				if (x > y) { return 1; }
				return 0;
			});
		}

		if (this.state.sortType === 'Rastuce') {
			this.state.clinics.reverse();
		}
		this.setState({
			refreshState: ''
		});

	}

	componentDidMount() {

		ClinicsService.getClinics().then((response) => {
			this.setState({ clinics: response.data });
		});

		this.setState({userRole: AuthenticationService.getRole()});
	}

	render() {

		return (

			<div style={{ margin: "auto" , width: '70%' }}>
				<h2 style={{ margin: "10px",textDecoration: "underline" }} className="text-center">Lista klinika</h2>
				<div className="row">

					<table className="table table-striped table bordered">

						<thead>
						<tr>
							<th className="sort" onClick={(param) => this.sort('naziv')}><u> Naziv </u></th>
							<th> Opis klinike</th>
							<th className="sort" onClick={(param) => this.sort('adresa')}><u> Adresa </u></th>
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
										{ this.state.userRole === "ROLE_PACIJENT" ?
											<td style={{ width: '35%' }}>
												<button onClick={() => this.viewClinic(clinic.idKlinike)} className="btn btn-info">Pregled</button>
											</td>
											:
											<td style={{ width: '35%' }}>
												<button onClick={() => this.viewClinic(clinic.idKlinike)} className="btn btn-info">Pregled</button>
												<button style={{ marginLeft: "10px" }} onClick={() => this.editClinic(clinic.idKlinike)} className="btn btn-default">Izmeni</button>
												<button style={{ marginLeft: "10px" }} onClick={() => this.deleteClinic(clinic.idKlinike)} className="btn btn-danger">Obrisi</button>
											</td>
										}

									</tr>
							)
						}
						</tbody>
					</table>
				</div>
				{ this.state.userRole !== "ROLE_PACIJENT" ?
					<div className="row">
						<button className="btn btn-primary" onClick={this.addClinic}> Dodaj kliniku</button>
					</div> : null
				}
			</div>

		)

	}
}

export default ListClinicsComponent