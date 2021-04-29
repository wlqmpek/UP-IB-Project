import { PacijentService } from "../../services/PacijentService";

const PacijentTableRow = ({patient, updatePatient, patients}) => {

    async function editPatient(id){
        try {
            await PacijentService.editPacijent(patient.idKorisnika, patient)

            updatePatient((patients) => patients.filter((patient) => patient.idKorisnika !== id))
        } catch (error){
            console.error(`Greska ${error}`)
        }
    }

    const block = (e) => {

        patient.statusKorisnika = "BLOKIRAN"
        editPatient(patient.idKorisnika)

    }

    return(
        <tr>
            <td>{patient.imeKorisnika}</td>
            <td>{patient.prezimeKorisnika}</td>
            <td>{patient.emailKorisnika}</td>
            <td><button className="btn btn-warning">Izmeni</button></td>
            <td><button className="btn btn-danger" onClick={block}>Blokiraj</button></td>
        </tr>
    )
}

export default PacijentTableRow