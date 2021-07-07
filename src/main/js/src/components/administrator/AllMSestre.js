import {MedicinskaSestraService} from "../../services/MedicinskaSestraService";
import {useEffect, useState} from "react";
import MedicinskaSestraTableRow from "../medicinskaSestra/MedicinskaSestraTableRow";
import {Link, Redirect, useHistory} from "react-router-dom";
import useSortableData from "../SortTable";

const AllMSestre = () => {

    const [msestre, setMSestre] = useState([])

    useEffect(() => {
        fetchMSestre()
    },[])

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
    const {items, requestSort, sortConfig} = useSortableData(msestre)
    const getClassNameFor = (name) => {
        if(!sortConfig){
            return;
        }
        return sortConfig.key === name ? sortConfig.direction : undefined
    }
    
    async function fetchMSestre(){
        try {
            const response = await MedicinskaSestraService.getMSestre()
            setMSestre(response.data)
        } catch (error){
            console.error(error)
        }
    }

    return(
        <div className="table-responsive" style={{marginTop: "100px"}}>
            <h2 className="text-center">Medicinske sestre</h2>
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
                {items.map((msestra, index) => (
                    <MedicinskaSestraTableRow key={index} msestra={msestra} msestre={items} updateMSestre={setMSestre}/>
                ))}
                </tbody>
            </table>
            <button className="btn btn-primary" onClick={redirect}>Dodaj</button>
        </div>
    )
}
export default AllMSestre