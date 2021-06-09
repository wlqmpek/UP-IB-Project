import React, { Component, useState } from 'react';
import { PacientService } from '../../services/PacientService';
import ClinicsService from '../../services/ClinicsService';
import PreglediService from '../../services/PreglediService';
import '../../App.css'

class MSHomePageComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            idKlinike: this.props.match.params.idKlinike,
            clinic: {},
            patients: [],
            pregledi: [],
            sortType: 'Rastuce',
            refreshState: ''
        }
        this.viewPatient = this.viewPatient.bind(this);
        this.viewMedSestra = this.viewMedSestra.bind(this);
        this.viewReceipts = this.viewReceipts.bind(this);
        this.viewWorkCalendar = this.viewWorkCalendar.bind(this);
        this.sort = this.sort.bind(this);
        this.changeSortTypeHandler = this.changeSortTypeHandler.bind(this);
    }

    changeSortTypeHandler = (event) => {
        this.setState({ 
            sortType: event.target.value
        });
    }

    componentDidMount() {
        ClinicsService.getClinicById(this.state.idKlinike).then(response => {
            this.setState({ clinic: response.data });
        }).catch(err => {
            return;
        })

        PreglediService.getPregledi().then((response) => {
            this.setState({ pregledi: response.data.filter(pregled => pregled.idKlinike === this.state.clinic.idKlinike) });
        })
            .catch(error => {

            })

        PacientService.getPacients().then((response) => {
            var allPatients = response.data;
            var i;
            for (i = 0; i < allPatients.length; i++) {
                var j;
                for (j = 0; j < this.state.pregledi.length; j++) {
                    if (allPatients[i].idZdravstvenogKartona === this.state.pregledi[j].idZdravstvenogKartona) {
                        this.state.patients.push(allPatients[i]);
                        break;
                    }
                }
            }
        });

        PacientService.getPacients().then((response) => {
            this.setState({ patients: response.data })
        });

    }

    viewPatient(id) {
        this.props.history.push(`/pacijenti/pregled/${id}`);
    }

    viewClinic(id) {
        this.props.history.push(`/klinike/pregled/${id}`);
    }

    viewMedSestra() {
        this.props.history.push(`/medicinske-sestre/${this.state.id}`);
    }

    viewReceipts() {
        this.props.history.push(`/medicinske-sestre/${this.state.id}/klinika/${this.state.idKlinike}/recepti`);
    }

    viewWorkCalendar() {
        this.props.history.push(`/${this.state.id}/radniKalendar/${this.state.clinic.idKlinike}`);
    }

    sort(param) {

        if (param === "jbzo") {
            this.state.patients.sort(function (a, b) {
                var x = a.jbzo.toLowerCase();
                var y = b.jbzo.toLowerCase();
                if (x < y) { return -1; }
                if (x > y) { return 1; }
                return 0;
            });
            // reverse jer se jbzo obrnuto sortira
            //this.state.patients.reverse();
        }
        else if (param === "ime") {
            this.state.patients.sort(function (a, b) {
                var x = a.ime.toLowerCase();
                var y = b.ime.toLowerCase();
                if (x < y) { return -1; }
                if (x > y) { return 1; }
                return 0;
            });
        }
        else if (param === "prezime") {
            this.state.patients.sort(function (a, b) {
                var x = a.prezime.toLowerCase();
                var y = b.prezime.toLowerCase();
                if (x < y) { return -1; }
                if (x > y) { return 1; }
                return 0;
            });
        }
        else if (param === "email") {
            this.state.patients.sort(function (a, b) {
                var x = a.email.toLowerCase();
                var y = b.email.toLowerCase();
                if (x < y) { return -1; }
                if (x > y) { return 1; }
                return 0;
            });
        }
        else if (param === "idZdravstvenogKartona") {
            this.state.patients.sort(function (a, b) {
                return a.idZdravstvenogKartona - b.idZdravstvenogKartona;
            });

            // reverse jer se id obrnuto sortira
            //this.state.patients.reverse();
        }
        this.state.patients.reverse();

        if (this.state.sortType === 'Rastuce') {
            this.state.patients.reverse();
        }
        this.setState({
            refreshState: ''
        });
    }

	render() {

		return (

            <div style={{ margin: "50px auto", width: '100%' }}>
                <div style={{ margin: "auto", width: '49%' }}>
                    <div className="row">
                        <button className="btn btn-primary" onClick={this.viewMedSestra}> Vas profil </button>
                    </div>
                    <div className="row">
					    <button className="btn btn-primary" onClick={this.viewWorkCalendar}> Radni kalendar</button>
				    </div>
				    <div className="row">
					    <button className="btn btn-primary" onClick={this.viewReceipts}> Overa recepata </button>
                    </div>
                </div>

                <div className="card col-md-6 offset-md-3">
                    <h3 style={{ margin: "10px" }} className="text-center"> Lista pacijenata klinike:&ensp; <a href="" onClick={() => this.viewClinic(this.state.clinic.idKlinike)} > {this.state.clinic.naziv} </a> </h3>
                    <div className="card-body">

                        {this.state.patients.length > 0 ? (
                            <div></div>
                        ) : (
                                <h3 style={{ margin: "10px" }} className="text-center">Ne postoje pacijenti</h3>
                            )}

                        <div className="row">
                            <table className="table table-striped table bordered">
                                <thead>
                                    <tr>
                                        <th className="sort" onClick={(param) => this.sort('jbzo')}><u> JBZO </u></th>
                                        <th className="sort" onClick={(param) => this.sort('ime')}><u> Ime </u></th>
                                        <th className="sort" onClick={(param) => this.sort('prezime')}><u> Prezime </u></th>
                                        <th className="sort" onClick={(param) => this.sort('email')}><u> Email </u></th>
                                        <th className="sort"  onClick={(param) => this.sort('idZdravstvenogKartona')}><u> ID Z.K. </u></th>
                                        <th style={{ width: '25%' }}>
                                            Sortiranje&emsp;
                                            <select onChange={this.changeSortTypeHandler}>
                                                <option>Rastuce</option>
                                                <option>Opadajuce</option>
                                            </select>
                                        </th>
                                    </tr>
                                </thead>

                                <tbody>
                                    {
                                        this.state.patients.map(
                                            patient =>
                                                <tr id="lista" key={patient.idKorisnika}>
                                                    <td>{patient.jbzo}</td>
                                                    <td>{patient.ime}</td>
                                                    <td>{patient.prezime}</td>
                                                    <td>{patient.email}</td>
                                                    <td>{patient.idZdravstvenogKartona}</td>
                                                    <td>
                                                        <button onClick={() => this.viewPatient(patient.idKorisnika)} className="btn btn-info">Pregled</button>
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

export default MSHomePageComponent;