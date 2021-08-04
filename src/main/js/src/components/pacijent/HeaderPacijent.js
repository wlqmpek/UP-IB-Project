import {Link} from "react-router-dom";
import {MDBIcon} from "mdbreact";
import React, {useEffect, useState} from "react";
import {useHistory} from "react-router";
import {AuthenticationService} from "../../services/AuthenticationService";
import PacijentService from "../../services/PacijentService";

const HeaderPacijent = () =>{

    const [pacijent, setPacijent] = useState({});
    const history = useHistory();
    const id = AuthenticationService.getId();

    useEffect(() => {
        fetchPacijent();
    }, [id]);

    async function fetchPacijent() {
        try {
            const response = await PacijentService.getPacijent(id);
            setPacijent(response.data);
        } catch (e) {
            console.log("Greska u HeaderPacijent " + e);

        }
    }

    const style = {
        border: 0,
        backgroundColor: "transparent"
    }

    const update = () => {
        console.log("Pacijent " + pacijent)
        history.push(`/pacijenti/profil/izmeni/${id}`)
    }

    const zdravstveniKarton = () => {
        console.log("Pacijent " + pacijent)
        history.push(`/zdravstveni-karton/${pacijent.idZdravstvenogKartona}`)
    }

    const mojiPregledi = () => {
        console.log("Pacijent " + pacijent)
        history.push(`/pregledi`)
    }

    const pretragaKlinika = () => {
        history.push(`/pretraga-klinika`);
    }

    return (
        <ul className="nav navbar-nav">
            <li className="nav-item"><Link className="nav-link" to="/">Početna</Link></li>
            <li className="nav-item"><button style={style} className="nav-link" onClick={update}><MDBIcon icon="user-edit"/> Vas profil</button></li>
            <li className="nav-item"><button style={style} className="nav-link" onClick={zdravstveniKarton}><MDBIcon icon="file-invoice" /> Zdravstveni Karton</button></li>
            <li className="nav-item"><button style={style} className="nav-link" onClick={mojiPregledi}><MDBIcon icon="tasks" /> Moji Pregledi</button></li>
            <li className="nav-item"><button style={style} className="nav-link" onClick={pretragaKlinika}><MDBIcon icon="search" /> Pretraga Klinika</button> </li>
        </ul>
    )
}

export default HeaderPacijent