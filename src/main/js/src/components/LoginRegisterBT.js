import {Link} from "react-router-dom";
import {MDBIcon} from "mdbreact";
import React from "react";

const LoginRegisterBT = () => {

    return(
        <ul className="nav navbar-nav">
            <li className="nav-item"><Link className="nav-link" to="/registracija"><MDBIcon icon="user-plus" />Registracija</Link></li>
            <li className="nav-item"><Link className="nav-link" to="/prijava"><MDBIcon icon="sign-in-alt" />Prijava</Link></li>
        </ul>
    )

}

export default LoginRegisterBT