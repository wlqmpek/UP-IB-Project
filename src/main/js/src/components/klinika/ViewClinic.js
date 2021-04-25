import React, { Component } from "react"
import ClinicsService from "../../services/ClinicsService";

class ViewClinicComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            clinic: {}
        }
    }

    componentDidMount() {
        ClinicsService.getClinicById(this.state.id).then(response => {
            this.setState({clinic: response.data});
        });
    }

    render() {
        return (
            <div>
                <div className="card col-md-6 offset-md-3">
                    <h3 className="text-center"> Pregled klinike </h3>
                    <div className="card-body">
                        <div className="row">
                            <label> Naziv klinike: </label>
                            <div> {this.state.clinic.naziv} </div>
                        </div>
                        <div className="row">
                            <label> Opis klinike: </label>
                            <div> {this.state.clinic.opis} </div>
                        </div>
                        <div className="row">
                            <label> Adresa klinike: </label>
                            <div> {this.state.clinic.adresa} </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewClinicComponent