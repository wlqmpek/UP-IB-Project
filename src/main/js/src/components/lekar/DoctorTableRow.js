import {LekarService} from "../../services/LekarService";
import {useHistory} from "react-router"
import ClinicsService from "../../services/ClinicsService";
import {useEffect, useState} from "react";

const DoctorTableRow = ({doctor, doctors, updateDoctor}) =>{

    const [clinic, setClinic] = useState({})
    const history = useHistory()

    useEffect(()=>{
        fetchClinic()
    },[])

    async function deleteDoctor(id){
        try {
            await LekarService.deleteLekar(doctor.idKorisnika)
            updateDoctor((doctor) => doctors.filter((doctor) => doctor.idKorisnika !== id))
        } catch (error){
            console.error(error)
        }
    }

    async function fetchClinic(){
        try {
            const respone = await ClinicsService.getClinicById(doctor.idKlinike)
            setClinic(respone.data)
        } catch (error){
            console.error(error)
        }
    }

    const update = (id) => {
        history.push("/lekari/"+ id)
    }

    const deleteDoc = (id) =>{
        deleteDoctor(id)
    }

    return(
        <tr>
            <td>{doctor.imeKorisnika}</td>
            <td>{doctor.prezimeKorisnika}</td>
            <td>{doctor.emailKorisnika}</td>
            <td>{clinic.naziv}</td>
            <td>
                <button className="btn btn-warning" onClick={()=>update(doctor.idKorisnika)}>Izmeni</button>
                <button className="btn btn-danger" onClick={()=>deleteDoc(doctor.idKorisnika)}>Obrisi</button>
            </td>
        </tr>
    )

}
export default DoctorTableRow