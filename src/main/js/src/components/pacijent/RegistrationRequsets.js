import { useState, useEffect } from "react"
import Request from "./Request"

const RegistrationRequsets = () => {

    const [zahtevi, setZahtevi] = useState([])

    useEffect(() => {
        const getRequests = async() => {
            const requests = await fetchRequests()
            setZahtevi(requests)
        }

        getRequests()
    }, [])

    const fetchRequests = async () => {
        const res = await fetch('http://localhost:8080/KlinickiCentar/Zahtevi')
        const data = await res.json()
    
        return data
      }

    return (
        <div className='table-responsive' style={{marginTop: "100px"}}>
            <h2 className="text-center">Zahtevi za registraciju</h2>
            <table className='table table-striped table bordered'>
                <thead >
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
                     <Request key={index} zahtev={zahtev} />
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default RegistrationRequsets
