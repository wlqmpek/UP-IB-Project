import {useEffect, useState} from "react";
import {useHistory} from "react-router"
import {MedicinkaSestraService} from "../../services/MedicinkaSestraService";
import ClinicsService from "../../services/ClinicsService";


const MedicinskaSestraTableRow = ({msestra, msestre, updateMSestre}) => {

    const [clinic, setClinic] = useState({})
    const history = useHistory()

    useEffect(() => {
        fetchClinic()
    },[])

    async function editMSestra(id){
        try {
            await MedicinkaSestraService.editMSestra(id, msestra)
            updateMSestre((msestra) => msestre.filter((msestra) => msestra.idKorisnika !== id))
        } catch (error){
            console.error(error)
        }
    }

    async function deleteMSestra(id){
        try {
            await MedicinkaSestraService.deleteMSestra(id)
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
        history.push("/medicinske-sestre"+ msestra.idKorisnika)
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