import {useEffect, useState} from "react";
import { PacijentService } from "../../services/PacijentService";
import PacijentTableRow from "../pacijent/PacijentTableRow";

const AllPacijents = () => {

    const [patiesnts, setPatients] = useState([])

    useEffect(() => {
        fetchPatients()
    }, [])

    async function fetchPatients() {
        try {
            const response = await PacijentService.getPacijents()
            setPatients(response.data)
        } catch (error){
            console.error(`Greska ${error}`)
        }
    }

    return(
        <div className="table-responsive" style={{marginTop: "100px"}}>
            <h2 className="text-center">Pacijenti</h2>
            <table className="table table-striped table border">
                <thead>
                    <tr>
                        <th>Ime</th>
                        <th>Prezime</th>
                        <th>Email</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {patiesnts.map((patient, index)=>(
                        <PacijentTableRow key={index} patient={patient} patients={patiesnts} updatePatient={setPatients}/>
                    ))}
                </tbody>
            </table>
            <button className="btn btn-primary">Dodaj</button>
        </div>
    )
}

export default AllPacijents