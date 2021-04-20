<<<<<<< HEAD
import { useState, useEffect } from "react"
import Request from "./Request"
// import GetPacijentRequset from "../../services/pacijent/GetPacijentRequests"
=======
import { useState } from "react";
>>>>>>> e51ab79c627de558ca8ad68ddb516a4e85d73dde

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
        <div className='table-responsive'>
            <table className='table'>
                <thead className='thead-dark'>
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
