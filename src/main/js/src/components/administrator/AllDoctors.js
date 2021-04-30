import {LekarService} from "../../services/LekarService";
import {useEffect, useState} from "react";
import DoctorTableRow from "../lekar/DoctorTableRow";
import {Link} from "react-router-dom";

const AllDoctors = () => {

    const [doctors, setDoctors] = useState([])

    useEffect(() => {
        fetchDoctors()
    },[])


    async function fetchDoctors(){
        try {
            const response = await LekarService.getLekars()
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
                    <th>Ime</th>
                    <th>Prezime</th>
                    <th>Email</th>
                    <th>Klinika</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {doctors.map((doctor, index) => (
                    <DoctorTableRow key={index} doctor={doctor} doctors={doctors} updateDoctor={setDoctors}/>
                ))}
                </tbody>
            </table>
            <button className="btn btn-primary"><Link to="/lekari/dodaj">Dodaj</Link></button>
        </div>
    )
}
export default AllDoctors