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
            <div style={{ margin: "50px" }}>
                <div className="card col-md-6 offset-md-3">
                    <h3 style={{ margin:"10px",textDecoration: "underline" }} className="text-center"> Pregled klinike </h3>
                    <div className="card-body">
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
                </div>
            </div>
        )
    }
}

export default ViewClinicComponent