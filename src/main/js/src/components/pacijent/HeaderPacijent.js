import {Link} from "react-router-dom";
import React from "react";

const HeaderPacijent = () =>{

    return (
        <ul className="nav navbar-nav">
            <li className="nav-item"><Link className="nav-link" to="/">Početna</Link></li>
        </ul>
    )
}

export default HeaderPacijent