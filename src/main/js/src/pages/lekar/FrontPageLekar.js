import React, {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {MDBIcon} from "mdbreact";
import {AuthenticationService} from "../../services/AuthenticationService";
import LekarService from "../../services/LekarService";
import EditLoggedDoctor from "./EditLoggedDoctor";
import {useHistory} from "react-router";

const FrontPageLekar = () => {

    const [lekar, setLekar] = useState({})
    const history = useHistory()

    const email = AuthenticationService.getEmail()

    const style = {
        width: 150
    }

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
        history.push("/lekar/izmena/"+ email)
    }

    function passwordChange(){

    }

    return(
        <div>
            <br/>
            <ul className="nav navbar-nav">
                <li style={style}><Link className="nav-link" to="/pacijenti"><MDBIcon icon="users"/>Pacijenti klinike</Link></li>
                <li style={style}><Link className="nav-link" to="/pregledi"><MDBIcon icon="user-md"/>Pregledi</Link></li>
                <li style={style}><Link className="nav-link" to="/radni-kalendar"><MDBIcon icon="calendar-alt"/>Radni kalendar</Link></li>
                <li style={style}><button style={{border: 0, backgroundColor: "transparent", color: "#0275d8"}} className="nav-link" onClick={update}><MDBIcon icon="user-edit"/>Vas profil</button></li>
                <li><Link className="nav-link" onClick={passwordChange}><MDBIcon icon="key" />Promenite lozinku</Link></li>
            </ul>
        </div>
    )
}

export default FrontPageLekar