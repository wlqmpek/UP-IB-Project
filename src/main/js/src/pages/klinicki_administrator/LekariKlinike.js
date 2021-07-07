import {useEffect, useState} from "react";
import LekarService from "../../services/LekarService";
import DoctorTableRow from "../../components/lekar/DoctorTableRow";
import useSortableData from "../../components/SortTable";
import {useHistory} from "react-router";

const LekariKlinike = () => {

    const [lekari, setLekari] = useState([])
    const history = useHistory()

    const styleButton = {
        border: "0",
        backgroundColor: "white",
        borderRadius: "none",
        fontFamily: "inherit",
        fontSize: "inherit"
    }

    const {items, requestSort, sortConfig} = useSortableData(lekari)
    const getClassNameFor = (name) => {
        if(!sortConfig){
            return;
        }
        return sortConfig.key === name ? sortConfig.direction : undefined
    }

    async function fetchLekari(){
        try {
            const response = await LekarService.getLekariByKlinika()
            setLekari(response.data)
        } catch (e){
            console.error(e)
        }
    }

    useEffect(()=>{
        fetchLekari()
    }, [])

    const add = () => {
        history.push("/klinika/lekar")
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
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {items.map((doctor, index) => (
                    <DoctorTableRow key={index} doctor={doctor} doctors={items} updateDoctor={setLekari}/>
                ))}
                </tbody>
            </table>
            <button className="btn btn-primary" onClick={add}>Dodaj</button>
        </div>
    )
}

export default LekariKlinike