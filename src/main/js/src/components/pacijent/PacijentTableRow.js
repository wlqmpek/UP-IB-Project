import {PacijentService} from "../../services/PacijentService";
import { useHistory } from "react-router"

const PacijentTableRow = ({patient, updatePatient, patients}) => {

    const history = useHistory();

    async function editPatient(id) {
        try {
            await PacijentService.editPacijent(patient.id, patient)
            updatePatient((patients) => patients.filter((patient) => patient.id !== id))
        } catch (error) {
            console.error(`Greska ${error}`)
        }
    }

    const block = () => {
        patient.statusKorisnika = "BLOKIRAN"
        editPatient(patient.id)

    }

    const update = (id) => {
        history.push("/pacijenti/"+ id)
    }

    return (
        <tr>
            <td>{patient.ime}</td>
            <td>{patient.prezime}</td>
            <td>{patient.email}</td>
            <td>{patient.statusKorisnika}</td>
            <td>{patient.jbzo}</td>
            <td>
                <button className="btn btn-warning" onClick={()=> update(patient.id)}>Izmeni</button>
            </td>
            <td>
                <button className="btn btn-danger" onClick={block}>Blokiraj</button>
            </td>
        </tr>
    )
}

export default PacijentTableRow