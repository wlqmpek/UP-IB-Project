import {useEffect, useState} from "react";
import {useHistory} from "react-router"
import { MedicinskaSestraService } from "../../services/MedicinskaSestraService";
import ClinicsService from "../../services/ClinicsService";


const MedicinskaSestraTableRow = ({msestra, msestre, updateMSestre}) => {

    const [clinic, setClinic] = useState({})
    const history = useHistory()

    useEffect(() => {
        fetchClinic()
    },[])

    async function editMSestra(id){
        try {
            await MedicinskaSestraService.editMSestra(id, msestra)
            updateMSestre((msestra) => msestre.filter((msestra) => msestra.idKorisnika !== id))
        } catch (error){
            console.error(error)
        }
    }

    async function deleteMSestra(id){
        try {
            await MedicinskaSestraService.deleteMSestra(id)
            updateMSestre((msestra) => msestre.filter((msestra) => msestra.idKorisnika !== id))
        } catch (error){
            console.error(error)
        }
    }

    async function fetchClinic(){
        try {
            const response = await ClinicsService.getClinicById(msestra.idKlinike)
            setClinic(response.data)
        } catch (error){
            console.error(error)
        }
    }

    const update = (id) =>{
        history.push("/medicinske-sestre/"+ msestra.idKorisnika)
    }

    const deleteMS = (id) => {
        deleteMSestra(id)
    }
    return(
        <tr>
            <td>{msestra.imeKorisnika}</td>
            <td>{msestra.prezimeKorisnika}</td>
            <td>{msestra.emailKorisnika}</td>
            <td>{clinic.naziv}</td>
            <td>
                <button className="btn btn-warning" onClick={()=>update(msestra.idKorisnika)}>Izmeni</button>
                <button className="btn btn-danger" onClick={()=>deleteMS(msestra.idKorisnika)}>Obrisi</button>
            </td>
        </tr>
    )
}

export default MedicinskaSestraTableRow