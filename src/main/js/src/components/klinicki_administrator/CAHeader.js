import React, {useEffect, useState} from "react";
import {useHistory} from "react-router";
import {AuthenticationService} from "../../services/AuthenticationService";
import AdminService from "../../services/AdminService";

const CAHeader = () => {

    const btnStyle = {border: 0, backgroundColor: "transparent", color: "#fff"}
    const history = useHistory()
    const [admin, setAdmin] = useState({})

    async function getAdmin() {
        try {
            const response = await AdminService.getLoggedAdmin()
            setAdmin(response.data)
        } catch (e){
            console.error(e)
        }
    }

    useEffect(()=>{
        getAdmin()
    }, [])

    const home = () => {
        history.push("/klinicki-administrator")
    }

    const clinic = () => {
        history.push(`/klinike/izmeni/${admin.idKlinike}`)
    }

    const info = () => {
        history.push("/korisnik/izmena")
    }

    const lekariClinic = () => {
        history.push("/lekari/klinika")
    }

    const pregledi = () => {
        history.push("/klinika/pregledi")
    }

    const cenovnik = () => {
        history.push("/cenovnik")
    }

    return(
        <ul className="nav navbar-nav">
            <li className="nav-item"><button onClick={home} style={btnStyle}>Pocetna</button></li>
            <li className="nav-item"><button onClick={pregledi} style={btnStyle}>Pregledi</button></li>
            <li className="nav-item"><button onClick={clinic} style={btnStyle}>Klinika</button></li>
            <li className="nav-item"><button onClick={lekariClinic} style={btnStyle}>Lekari</button></li>
            <li className="nav-item"><button onClick={cenovnik} style={btnStyle}>Cenovnik</button></li>
            <li className="nav-item"><button onClick={info} style={btnStyle}>Vase informacije</button></li>
        </ul>
    )
}

export default CAHeader