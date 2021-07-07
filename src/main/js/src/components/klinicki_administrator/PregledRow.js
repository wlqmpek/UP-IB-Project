import {useEffect, useState} from "react";
import LekarService from "../../services/LekarService";
import {MedicinskaSestraService} from "../../services/MedicinskaSestraService";
import {useHistory} from "react-router";
import PreglediService from "../../services/PreglediService";

const PregledRow = ({pregled, pregledi, setPregledi}) => {

    const [lekar, setLekar] = useState({})
    const [MSestra, setMSestra] = useState({})
    const [free, setFree] = useState("")
    const history = useHistory()

    async function fetchLekar(){
        try {
            const response = await LekarService.getLekar(pregled.idLekara)
            setLekar(response.data)
        } catch (e){
            console.error(e)
        }
    }

    async function fetchMSestra() {
        try {
            const response = await MedicinskaSestraService.getMSestra(pregled.idMedicinskeSestre)
            setMSestra(response.data)
        }catch (e) {
            console.error(e)
        }
    }

    async function removePregled() {
        try {
            await PreglediService.deletePregled(pregled.idPregleda)
            setPregledi(()=> pregledi.filter((p) => p.idPregleda !== pregled.idPregleda))
        } catch (e) {
            console.error(e)
        }
    }

    useEffect(()=>{
        fetchLekar()
        fetchMSestra()
        status()
    },[])

    const status = () => {
        if(pregled.idZdravstvenogKartona !== undefined){
            return setFree("Slobodan")
        }
        return setFree("Zakazan")
    }

    const update = () => {
        history.push(`/pregled/izmeni/${pregled.idPregleda}`)
    }

    const remove = () => {
        removePregled()
    }

    return(
        <tr>
            <td>{lekar.imeKorisnika + " " + lekar.prezimeKorisnika}</td>
            <td>{MSestra.imeKorisnika + " " + MSestra.prezimeKorisnika}</td>
            <td>{pregled.pocetakTermina}</td>
            <td>{pregled.krajTermina}</td>
            <td>{pregled.cena}</td>
            <td>{free}</td>
            {free === "Slobodan" ? <button onClick={update} className="btn btn-warning">Izmeni</button> : null}
            {free === "Slobodan" ? <button onClick={remove} className="btn btn-danger">Obrisi</button> : null}
        </tr>
    )
}

export default PregledRow