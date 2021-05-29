import React from 'react'
import {RegistrationRequestService} from "../../services/RegistrationRequestService";

const Request = ({zahtev, updateZahtevi, zahtevi}) => {

    async function editRequests(id) {
        try {
            await RegistrationRequestService.editRequest(zahtev.id, zahtev)

            updateZahtevi((zahtevi) => zahtevi.filter((zahtev) => zahtev.id !== id))
        } catch (error) {
            console.error(`Greska ${error}`)
        }
    }

    const accept = (e) => {

        zahtev.statusKorisnika = "PRIHVACEN"
        editRequests(zahtev.id)

    }

    const decline = (e) => {

        zahtev.statusKorisnika = "ODBIJEN"
        editRequests(zahtev.id)

    }

    return (
        <tr>
            <td>{zahtev.ime}</td>
            <td>{zahtev.prezime}</td>
            <td>{zahtev.email}</td>
            <td>
                <button className="btn btn-success" onClick={accept}>Prihvati</button>
            </td>
            <td>
                <button className="btn btn-danger" onClick={decline}>Odbij</button>
            </td>
        </tr>
    )
}

export default Request
