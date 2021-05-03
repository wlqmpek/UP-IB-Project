import React, { Component } from "react"
import ClinicsService from "../../services/ClinicsService";
import PreglediService from "../../services/PreglediService";
import LekarService from "../../services/LekarService";
import MedSestraService from "../../services/MedSestraService";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

class ViewWorkCalendar extends Component {

    constructor(props) {
        super(props)

        this.state = {
            idKorisnika: this.props.match.params.idKorisnika,
            idKlinike: this.props.match.params.idKlinike,
            korisnik: {},
            tipKorisnika: "",
            clinic: {},
            sviDatumi: [],
            sviPregledi: [],
            preglediZaDatum: [],
            selektovaniDatum: "",
            dayClicked: true
        }
        this.onChange = this.onChange.bind(this);
        this.onClickDay = this.onClickDay.bind(this);
    }

    componentDidMount() {
        /*if (this.state.tipKorisnika === "MEDICINSKA_SESTRA") {
            MedSestraService.getMedSestra(this.state.idKorisnika).then(response => {
                this.setState({
                    korisnik: response.data
                })
            }).catch(err => {
                return;
            })
        }
        
        else if (this.state.tipKorisnika === "LEKAR") {
            LekarService.getLekar(this.state.idKorisnika).then(response => {
                this.setState({
                    korisnik: response.data
                })
            }).catch(err => {
                return;
            })
        }

        else {
            // ako nije ni medicinska sestra ni lekar onda ne treba da se prikazuje radni kalendar
            // jer se ne radi o medicinskom osoblju
            return;
        }*/

        ClinicsService.getClinicById(this.state.idKlinike).then(response => {
            this.setState({ clinic: response.data });
        }).catch(err => {
            return;
        })

        PreglediService.getPregledi().then(response => {
            var pregledi = response.data.filter(pregled => pregled.idKlinike == this.state.clinic.idKlinike);//&& pregled.idMedicinskeSestre == this.state.idKorisnika);
            //var pregledi = response.data;

            var datumi = []
            pregledi.forEach(pregled => {
                var datum = new Date(pregled.pocetakTermina);
                datumi.push(datum);
            });
            
            this.setState({
                sviPregledi: pregledi,
                sviDatumi: datumi
            });

            this.onChange(new Date());
        });
        
    }

    onChange(selektovaniDatum) {
        // OPERACIJE
        if (this.state.dayClicked) {
            console.log("DAY CLICKED");
            // SAMO AKO JE DAN KLINKNUT SE POSTAVLJA DATUM

            var updateovaniPregledi = [];

            this.state.sviPregledi.forEach(pregled => {
                var datum = new Date(pregled.pocetakTermina);
                console.log(selektovaniDatum);

                if (datum.getDate() === selektovaniDatum.getDate() && datum.getMonth() === selektovaniDatum.getMonth() && datum.getYear() === selektovaniDatum.getYear()) {
                    updateovaniPregledi.push(pregled);
                }
            })

            // updateovanje pregleda i resetovanje dayClicked
            this.setState({
                preglediZaDatum: updateovaniPregledi,
                dayClicked: false,
                selektovaniDatum: new Date(selektovaniDatum).toDateString()
            })

        }
        
    }

    onClickDay() {
        this.setState({
            dayClicked: true
        })
    }


    updatePregled(id) {
        this.props.history.push(`/pregledi/${id}/azuriraj`);
    }

    render() {
        return (
            <div>
                <div style={{ 'margin-top': "40px" }}>
                    <div className="col-md-6 offset-md-3" >
                        <div className="card-body" style={{ margin: 'auto' }} >

                            <div style={{ 'margin-left': '20px', width: '45%', float: 'right', margin:'auto' }}>
                                <br />
                                <br />
                                <br />
                                <br />
                                <h4> <span style={{ color: 'rgb(255, 255, 115)', 'background-color': 'rgb(255, 255, 115)' }}> AA</span> Današnji dan ({new Date().toDateString()})</h4>
                                <br />
                                <h4> <span style={{ color: 'rgb(118, 186, 255)', 'background-color':'rgb(118, 186, 255)' }}> AA</span> Dani sa pregledima </h4>
                            </div>


                            <div style={{ width: '55%' }}>
                                <h3 style={{ margin: 'auto' }} > Radni kalendar </h3>
                                <Calendar
                                    minDetail="decade"
                                    onClickDay={(value, event) => this.onClickDay()}
                                    onChange={(value, event) => this.onChange(value)}
                                    value={this.state.sviDatumi.length === 0 ? false : this.state.sviDatumi}
                                />
                            </div>
                            
                        </div>

                    </div>
                </div>
                <br />

                <h3 style={{ margin: 'auto', textAlign: 'center' }} > Lista pregleda za datum:&nbsp; <a href="">{this.state.selektovaniDatum}</a></h3>

                <div style={{ 'margin': '20px auto', width: '70%', padding:'0 25px' }} className="card" >
                    <div className="row">
                        <div className="card-body">
                            <table className="table table-striped table bordered">

                                <thead>
                                    <tr>
                                        <th> Vreme početka </th>
                                        <th> Trajanje </th>
                                        <th> Ime pacijenta </th>
                                        <th> Prezime pacijenta </th>
                                        <th></th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                    {
                                        this.state.preglediZaDatum.map(
                                            pregled =>
                                                <tr id="lista" key={pregled.idPregleda}>
                                                    <td style={{ verticalAlign: 'middle', fontSize:'20px' }}>{
                                                        new Date(pregled.pocetakTermina).toISOString().split("T")[1].slice(0,5) + "h"
                                                    }</td>
                                                    <td style={{ verticalAlign: 'middle', fontSize: '20px' }}>
                                                        {
                                                            // ako je pregled zavrsen (kraj termina je definisan)
                                                            pregled.krajTermina != null ?
                                                                Math.round(Math.abs(new Date(pregled.krajTermina).getTime() - new Date(pregled.pocetakTermina).getTime()) / 60000)
                                                                : false  
                                                        }
                                                        {
                                                            // ako je pregled zavrsen (kraj termina je definisan)
                                                            pregled.krajTermina != null ?
                                                                (<span>&nbsp;min&nbsp;(završen)</span>)
                                                                : false
                                                        }
                                                        {
                                                            // ako kraj termina nije definisan a pregled je poceo
                                                            pregled.krajTermina === null && new Date(pregled.pocetakTermina).getTime() <= new Date().getTime() ?
                                                                (<span>Pregled je u toku</span>)
                                                                : false
                                                        }
                                                        {
                                                            // ako kraj termina nije definisan a pregled je u buducnosti
                                                            pregled.krajTermina === null && new Date(pregled.pocetakTermina).getTime() > new Date().getTime() ?
                                                                (<span>Pregled nije počeo</span>)
                                                                : false
                                                        }

                                                        
                                                    </td>
                                                    <td style={{ verticalAlign: 'middle', fontSize: '20px' }}></td>
                                                    <td style={{ verticalAlign: 'middle', fontSize: '20px' }}></td>
                                                    <td style={{ width: '20%' }}>
                                                        {new Date(pregled.pocetakTermina).getTime() <= new Date().getTime() && pregled.krajTermina === null ? ( // && this.state.tipKorisnika === "LEKAR"? (
                                                        <button onClick={() => this.updatePregled(pregled.idPregleda)} className="btn btn-info">Azuriraj</button>
                                                        )
                                                        : (
                                                            <div />
                                                            )
                                                        }
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

export default ViewWorkCalendar;
