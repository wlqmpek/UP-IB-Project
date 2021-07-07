import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {MDBIcon} from "mdbreact";
import {AuthenticationService} from "../../services/AuthenticationService";
import LekarService from "../../services/LekarService";
import EditLoggedUser from "../EditLoggedUser";
import {useHistory} from "react-router";

const FrontPageLekar = () => {

    const [lekar, setLekar] = useState({})
    const history = useHistory()

    const email = AuthenticationService.getEmail()

    const style = { width: 150 }

    useEffect(() => {
        fetchLekar(email)
        }, [email])

    async function fetchLekar(email){
        try {
            const response = await LekarService.getLekarByEmail(email)
            setLekar(response.data)
        }catch (e){

        }
    }

    const update = () => {
        console.log(lekar)
        history.push("/korisnik/izmena")
    }

    function passwordChange(){
        history.push("/korisnik/promena-lozinke")
    }

    const workCalendar = () => {
        history.push(`/${lekar.idKorisnika}/radniKalendar/${lekar.idKlinike}`)
    }

    const appointment = () => {
        history.push("/lekar/pregledi/dodaj")
    }

    const btnStyle = {border: 0, backgroundColor: "transparent", color: "#0275d8"}

    return(
        <div>
            <br/>
            <ul className="nav navbar-nav">
                <li style={style}><Link className="nav-link" to="/pacijenti"><MDBIcon icon="users"/>Pacijenti klinike</Link></li>
                <li style={style}><button style={btnStyle} className="nav-link" onClick={appointment}><MDBIcon icon="user-md"/>Pregledi</button></li>
                <li style={style}><button style={btnStyle} className="nav-link" onClick={workCalendar} ><MDBIcon icon="calendar-alt"/>Radni kalendar</button></li>
                <li style={style}><button style={btnStyle} className="nav-link" onClick={update}><MDBIcon icon="user-edit"/>Vas profil</button></li>
                <li><button style={btnStyle} className="nav-link" onClick={passwordChange}><MDBIcon icon="key" />Promenite lozinku</button></li>
            </ul>
        </div>
    )
}

export default FrontPageLekar