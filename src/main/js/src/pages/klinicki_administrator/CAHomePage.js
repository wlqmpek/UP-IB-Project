import {useHistory} from "react-router";
import {useEffect, useState} from "react";
import AdminService from "../../services/AdminService";

const CAHomePage = () => {

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

    const btnStyle = {border: 0, backgroundColor: "transparent", color: "#0275d8"}
    const style = { width: 150 }

    const clinic = () => {
        history.push(`/klinike/izmeni/${admin.idKlinike}`)
    }

    const info = () => {
        history.push("/korisnik/izmena")
    }

    const pregledi = () => {
        history.push("/klinika/pregledi")
    }

    const addPregled = () => {
        history.push("/admini/pregledi/dodaj")
    }

    const changePassword = () => {
        history.push("/korisnik/promena-lozinke")
    }

    const lekariClinic = () => {
        history.push("/lekari/klinika")
    }

    const cenovnik = () => {
        history.push("/cenovnik")
    }
    return(
        <div className="container-fluid">
            <ul>
                <li style={style}><button style={btnStyle} onClick={pregledi} className="nav-link">Pregledi</button></li>
                <li style={style}><button style={btnStyle} onClick={addPregled} className="nav-link">Dodaj pregled</button></li>
                <li style={style}><button style={btnStyle} onClick={clinic} className="nav-link">Klinika</button></li>
                <li style={style}><button style={btnStyle} onClick={lekariClinic} className="nav-link">Lekari</button></li>
                <li style={style}><button style={btnStyle} onClick={cenovnik} className="nav-link">Cenovnik</button></li>
                <li style={style}><button style={btnStyle} onClick={info} className="nav-link">Vase informacije</button></li>
                <li style={style}><button style={btnStyle} onClick={changePassword} className="nav-link">Promena lozinke</button></li>
            </ul>
        </div>
    )
}

export default CAHomePage