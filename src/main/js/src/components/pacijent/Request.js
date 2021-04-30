import React from 'react'
import {RegistrationRequestService} from "../../services/RegistrationRequestService";

const Request = ({zahtev, updateZahtevi, zahtevi}) => {

    async function editRequests(id){
        try {
            await RegistrationRequestService.editRequest(zahtev.idKorisnika, zahtev)

            updateZahtevi((zahtevi)=> zahtevi.filter((zahtev) => zahtev.idKorisnika !== id))
        } catch (error){
            console.error(`Greska ${error}`)
        }
    }

    const prihvati = (e)=>{
        
        zahtev.statusKorisnika = "PRIHVACEN"
        console.log(zahtev)
        editRequests(zahtev.idKorisnika)

    }

    const odbij = (e) => {
        
        zahtev.statusKorisnika = "ODBIJEN"
        editRequests(zahtev.idKorisnika)

    }
    
    return (
        <tr>
            <td>{zahtev.imeKorisnika}</td>
            <td>{zahtev.prezimeKorisnika}</td>
            <td>{zahtev.emailKorisnika}</td>
            <td><button className="btn btn-success" onClick={prihvati}>Prihvati</button></td>
            <td><button className="btn btn-danger" onClick={odbij}>Odbij</button></td>
        </tr>
    )
}

export default Request
