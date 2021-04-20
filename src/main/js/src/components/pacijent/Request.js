import React from 'react'

const Request = ({zahtev}) => {

    function obrada(zahtev) {

        const izmena = async (zahtev) => {
            const rez = await fetch(`http://localhost:8080/KlinickiCentar/Zahtevi/${zahtev.idKorisnika}`, {
                method: "PUT",
                headers: {
                    'Content-type': 'application/json',
                  },
                  body: JSON.stringify(zahtev),
            })
            const podaci = await rez.json()
        }
        izmena(zahtev)

        window.location.reload(false)
    }

    const prihvati = (e)=>{
        
        zahtev.statusKorisnika = "PRIHVACEN"
        obrada(zahtev)
    }

    const odbij = (e) => {
        
        zahtev.statusKorisnika = "ODBIJEN"
        obrada(zahtev)
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
