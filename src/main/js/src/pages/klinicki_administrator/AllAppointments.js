import {useEffect, useState} from "react";
import PreglediService from "../../services/PreglediService";
import PregledRow from "../../components/klinicki_administrator/PregledRow";

const AllAppointments = () => {

    const [pregledi, setPregledi] = useState([])

    async function fetchPregledi(){
        try {
            const response = await PreglediService.getPreglediForKlinika()
            setPregledi(response.data)
        }catch (e) {
            console.error(e)
        }
    }

    useEffect(()=>{
        fetchPregledi()
    }, [])

    return(
        <div className="table-responsive" style={{marginTop: "100px"}}>
            <h2 className="text-center">Pregledi</h2>
            <table className="table table-striped table border">
                <thead>
                    <tr>
                        <th>Lekar</th>
                        <th>Medicinska sestra</th>
                        <th>Datum pocetka</th>
                        <th>Datum kraja</th>
                        <th>Cena</th>
                        <th>Status</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                {pregledi.map((pregled, index) =>
                    (<PregledRow key={index} pregled={pregled} pregledi={pregledi} setPregledi={setPregledi}/> )
                )}
                </tbody>
            </table>
        </div>
    )
}

export default AllAppointments