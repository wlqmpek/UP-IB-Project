import React, { Component } from "react"
import ClinicsService from "../../services/ClinicsService";
import PreglediService from "../../services/PreglediService";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

class ViewWorkCalendar extends Component {

    constructor(props) {
        super(props)

        this.state = {
            idKorisnika: this.props.match.params.idKorisnika,
            idKlinike: this.props.match.params.idKlinike,
            clinic: {},
            sviDatumi: new Date(),
            sviPregledi: [],
            preglediZaDatum: [],
            selektovaniDatum: "",
            dayClicked: true
        }
        this.onChange = this.onChange.bind(this);
        this.onClickDay = this.onClickDay.bind(this);
    }

    componentDidMount() {

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

    render() {
        return (
            <div>
                <div style={{ margin: "40px auto auto" }}>
                    <div className="col-md-6 offset-md-3">
                        <div className="card-body" style={{ margin: 'auto' }} >
                            <h3 style={{ margin:'auto' }} > Radni kalendar </h3>
                            <Calendar
                                minDetail="decade"
                                onClickDay={(value, event) => this.onClickDay()}
                                onChange={(value, event) => this.onChange(value)}
                                value={this.state.sviDatumi}
                            />
                        </div>
                    </div>
                </div>

                <h3 style={{ margin: 'auto', textAlign: 'center' }} > Lista pacijenata za datum:&nbsp; <a href="">{this.state.selektovaniDatum}</a></h3>

                <div style={{ 'margin': '20px auto' }} className="card col-md-6 offset-md-3" >
                    <div className="row">
                        <div className="card-body">
                            <table className="table table-striped table bordered">

                                <thead>
                                    <tr>
                                        <th> Vreme početka </th>
                                        <th> Trajanje </th>
                                        <th> Ime pacijenta </th>
                                        <th> Prezime pacijenta </th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                    {
                                        this.state.preglediZaDatum.map(
                                            pregled =>
                                                <tr id="lista" key={pregled.idPregleda}>
                                                    <td>{pregled.pocetakTermina.replace('T', ' ')}</td>
                                                    <td>{Math.abs(new Date(pregled.krajTermina).getTime() - new Date(pregled.pocetakTermina).getTime()) / 60000}&nbsp;min</td>
                                                    <td></td>
                                                    <td></td>
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
