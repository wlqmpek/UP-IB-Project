import {Link} from "react-router-dom";
import React from "react";

const HeaderGuest = ()=>{

    return(
        <ul className="nav navbar-nav">
            <li className="nav-item"><Link className="nav-link" to="/">Početna</Link></li>
            <li className="nav-item"><Link className="nav-link" to="/klinike">Klinike</Link></li>
            <li className="nav-item"><Link className="nav-link" to="/">Cenovnik</Link></li>
        </ul>
    )
}

export default HeaderGuest