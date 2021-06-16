import {Link} from "react-router-dom";
import {MDBIcon} from "mdbreact";
import React, {useEffect, useState} from "react";
import {useHistory} from "react-router";
import {AuthenticationService} from "../../services/AuthenticationService";

const HeaderLekar = () => {

    const [lekar, setLekar] = useState()
    const history = useHistory()

    useEffect(() => {
        setLekar(AuthenticationService.getEmail())
    })

    const style = {
        border: 0,
        backgroundColor: "transparent"
    }

    const update = () => {
        console.log(lekar)
        history.push("/lekar/izmena/"+ lekar)
    }


    return(
        <ul className="nav navbar-nav">
            <li className="nav-item"><Link className="nav-link" to="/lekar-pocetna"><MDBIcon icon="home"/>Pocetna</Link></li>
            <li className="nav-item"><Link className="nav-link" to="/pacijenti"><MDBIcon icon="users"/>Pacijenti klinike</Link></li>
            <li className="nav-item"><Link className="nav-link" to="/pregledi"><MDBIcon icon="user-md"/>Pregledi</Link></li>
            <li className="nav-item"><Link className="nav-link" to="/radni-kalendar"><MDBIcon icon="calendar-alt"/>Radni kalendar</Link></li>
            <li className="nav-item"><button style={style} className="nav-link" onClick={update}><MDBIcon icon="user-edit"/>Vas profil</button></li>
        </ul>
    )
}

export default HeaderLekar