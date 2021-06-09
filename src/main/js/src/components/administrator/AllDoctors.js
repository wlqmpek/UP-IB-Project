import {LekarService} from "../../services/LekarService";
import {useEffect, useState} from "react";
import DoctorTableRow from "../lekar/DoctorTableRow";
import {Link, useHistory} from "react-router-dom";
import useSortableData from "../SortTable";

const AllDoctors = () => {

    const [doctors, setDoctors] = useState([])

    useEffect(() => {
        fetchDoctors()
    },[])

    const styleButton = {
        border: "0",
        backgroundColor: "white",
        borderRadius: "none",
        fontFamily: "inherit",
        fontSize: "inherit"
    }

    const history = useHistory()
    const redirect = () => {
        return history.push("/lekari/dodaj")
    }

    const {items, requestSort, sortConfig} = useSortableData(doctors)
    const getClassNameFor = (name) => {
        if(!sortConfig){
            return;
        }
        return sortConfig.key === name ? sortConfig.direction : undefined
    }

    async function fetchDoctors(){
        try {
            const response = await LekarService.getLekari()
            setDoctors(response.data)
        } catch (error){
            console.error(error)
        }
    }

    return(
        <div className="table-responsive" style={{marginTop: "100px"}}>
            <h2 className="text-center">Lekari</h2>
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
                    <th>
                        <button
                            type="button"
                            style={styleButton}
                            onClick={() => requestSort("klinika")}
                            className={getClassNameFor("klinika")}
                        >
                            Klinika
                        </button>
                    </th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {items.map((doctor, index) => (
                    <DoctorTableRow key={index} doctor={doctor} doctors={items} updateDoctor={setDoctors}/>
                ))}
                </tbody>
            </table>
            <button className="btn btn-primary" onClick={redirect}>Dodaj</button>
        </div>
    )
}
export default AllDoctors