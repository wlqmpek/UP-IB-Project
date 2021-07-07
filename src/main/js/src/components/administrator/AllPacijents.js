import {useEffect, useState} from "react";
import {PacijentService} from "../../services/PacijentService";
import PacijentTableRow from "../pacijent/PacijentTableRow";
import useSortableData from "../SortTable";
import {useHistory} from "react-router-dom";

const AllPacijents = () => {

    const [patients, setPatients] = useState([])
    const [searchName, setSearchName] = useState("")
    const [searchLastName, setSearchLastName] = useState("")
    const [searchJBZO, setSearchJBZO] = useState("")

    useEffect(() => {
        fetchPatients()
    }, [])

    const styleButton =  {
        border: "0",
        backgroundColor: "white",
        borderRadius: "none",
        fontFamily: "inherit",
        fontSize: "inherit"
    }

    const history = useHistory()
    const redirect = () => {
        return history.push("/medicinske-sestre/dodaj")
    }

    const {items, requestSort, sortConfig} = useSortableData(patients)
    const getClassNameFor = (name) => {
        if(!sortConfig){
            return;
        }
        return sortConfig.key === name ? sortConfig.direction : undefined
    }

    async function fetchPatients() {
        try {
            const response = await PacijentService.getPacijents()
            setPatients(response.data)
        } catch (error) {
            console.error(`Greska ${error}`)
        }
    }

    function filterPatients(){
        if(searchName.length && searchLastName.length && searchJBZO.length !== 0){
            return  items.filter(patient => patient.ime.toLowerCase()
                    .includes(searchName.toLowerCase()) &&
                patient.prezime.toLowerCase()
                    .includes(searchLastName.toLowerCase()) &&
            patient.jbzo.includes(searchJBZO))
        }
        if(searchName.length && searchLastName.length !== 0){
            return  items.filter(patient => patient.ime.toLowerCase()
                .includes(searchName.toLowerCase()) &&
                patient.prezime.toLowerCase()
                    .includes(searchLastName.toLowerCase()))
        }
        if(searchName.length && searchJBZO.length !== 0){
            return  items.filter(patient => patient.ime.toLowerCase()
                    .includes(searchName.toLowerCase()) &&
                patient.jbzo.toLowerCase()
                    .includes(searchJBZO.toLowerCase()))
        }
        if(searchLastName.length && searchJBZO.length !== 0){
            return items.filter(patient => patient.prezime.toLowerCase()
                .includes(searchLastName.toLowerCase()) &&
            patient.jbzo.includes(searchJBZO))
        }
        if(searchName.length !== 0){
            return items.filter(patient => patient.ime.toLowerCase()
                    .includes(searchName.toLowerCase()))
        }
        if(searchLastName.length !== 0){
            return items.filter(patient => patient.prezime.toLowerCase()
                .includes(searchLastName.toLowerCase()))
        }
        if(searchJBZO.length !== 0){
            return items.filter(patient => patient.jbzo.toLowerCase()
                .includes(searchJBZO.toLowerCase()))
        }

        return items
    }

    const filteredPatients = filterPatients()
        // searchName.length && searchLastName.length && searchJBZO.length === 0 ? patients :
        // items.filter(patient => patient.ime.toLowerCase()
        //     .includes(searchName.toLowerCase()))

    return (
        <div className="table-responsive" style={{marginTop: "100px"}}>
            <h2 className="text-center">Pacijenti</h2>
            <label>Ime: </label>
            <input
                type="search"
                className="form-control-sm rounded"
                placeholder="Pretraga po imenu"
                value={searchName}
                onChange={(e) => setSearchName(e.target.value) && filteredPatients}
            />
            <label>Prezime: </label>
            <input
                type="search"
                className="form-control-sm rounded"
                placeholder="Pretraga po prezimenu"
                value={searchLastName}
                onChange={(e) => setSearchLastName(e.target.value) && filteredPatients}
            />
            <label>JBZO:</label>
            <input
                type="search"
                className="form-control-sm rounded"
                placeholder="Pretraga po JBZO"
                value={searchJBZO}
                onChange={(e) => setSearchJBZO(e.target.value) && filteredPatients}
            />
            <table className="table table-striped table border">
                <thead>
                <tr>
                    <th>
                        <button
                            type="button"
                            style={styleButton}
                            onClick={() => requestSort("ime")}
                            className={getClassNameFor("ime")}
                        >
                            Ime
                        </button>
                    </th>
                    <th>
                        <button
                            type="button"
                            style={styleButton}
                            onClick={() => requestSort("prezime")}
                            className={getClassNameFor("prezime")}
                        >
                            Prezime
                        </button>
                    </th>
                    <th>
                        <button
                            type="button"
                            style={styleButton}
                            onClick={() => requestSort("email")}
                            className={getClassNameFor("email")}
                        >
                            Email
                        </button>
                    </th>
                    <th>Status</th>
                    <th>JBZO</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {filteredPatients.map((patient, index) => (
                    <PacijentTableRow key={index} patient={patient} patients={filteredPatients} updatePatient={setPatients}/>
                ))}
                </tbody>
            </table>
            <button className="btn btn-primary" onClick={redirect}>Dodaj</button>
        </div>
    )
}

export default AllPacijents