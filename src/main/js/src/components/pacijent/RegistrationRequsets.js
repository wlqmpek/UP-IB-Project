import {useState, useEffect} from "react"
import Request from "./Request"
import {RegistrationRequestService} from "../../services/RegistrationRequestService";

const RegistrationRequsets = () => {

    const [zahtevi, setZahtevi] = useState([])

    useEffect(() => {
        fetchRequests()
    }, [])

    async function fetchRequests() {
        try {
            const response = await RegistrationRequestService.getRequests()
            setZahtevi(response.data)
        } catch (error) {
            console.error(`Greska ${error}`)
        }
    }

    return (
        <div className='table-responsive' style={{marginTop: "100px"}}>
            <h2 className="text-center">Zahtevi za registraciju</h2>
            <table className='table table-striped table bordered'>
                <thead>
                <tr>
                    <th scope='row'>Ime</th>
                    <th scope='row'>Prezime</th>
                    <th scope='row'>Email</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {zahtevi.map((zahtev, index) => (
                    <Request key={index} zahtev={zahtev} updateZahtevi={setZahtevi} zahtevi={zahtevi}/>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default RegistrationRequsets
