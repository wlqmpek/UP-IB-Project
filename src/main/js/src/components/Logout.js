import {Link} from "react-router-dom";
import {MDBIcon} from "mdbreact";
import React from "react";
import {AuthenticationService} from "../services/AuthenticationService";
import {Button} from "react-bootstrap";


const Logout = () => {

    const styleButton = {
        border: "0",
        borderRadius: "none",
    }

    return(
        <ul className="nav navbar-nav">
            <li className="nav-item">
                <button className="btn-primary" style={styleButton} onClick={AuthenticationService.logout}>
                    <MDBIcon icon="sign-out-alt"/>Odjava
                </button>
            </li>
        </ul>
    )
}

export default Logout