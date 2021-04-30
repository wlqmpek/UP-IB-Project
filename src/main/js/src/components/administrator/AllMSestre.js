import {MedicinkaSestraService} from "../../services/MedicinkaSestraService";
import {useEffect, useState} from "react";
import MedicinskaSestraTableRow from "../medicinska_sestra/MedicinskaSestraTableRow";
import {Link} from "react-router-dom";

const AllMSestre = () => {

    const [msestre, setMSestre] = useState([])

    useEffect(() => {
        fetchMSestre()
    },[])
    async function fetchMSestre(){
        try {
            const response = await MedicinkaSestraService.getMSestre()
            setMSestre(response.data)
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
                    <th>Ime</th>
                    <th>Prezime</th>
                    <th>Email</th>
                    <th>Klinika</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {msestre.map((msestra, index) => (
                    <MedicinskaSestraTableRow key={index} msestra={msestra} msestre={msestre} updateMSestre={setMSestre}/>
                ))}
                </tbody>
            </table>
            <button className="btn btn-primary"><Link to="/medicinske-sestre/dodaj"/>Dodaj</button>
        </div>
    )
}
export default AllMSestre