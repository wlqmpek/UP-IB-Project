import React from 'react'

const Request = ({zahtev}) => {

    const prihvati = (e)=>{
        
        zahtev.statusKorisnika = "PRIHVACEN"
        console.log(zahtev.statusKorisnika)
    }

    const odbij = (e) => {
        
        zahtev.statusKorisnika = "ODBIJEN"
    }
    
    return (
        <tr>
            <td>{zahtev.imeKorisnika}</td>
            <td>{zahtev.prezimeKorisnika}</td>
            <td>{zahtev.emailKorisnika}</td>
            <td><button className="btn btn-success" onClick={prihvati}>Prihvati</button></td>
            <td><button className="btn btn-danger">Odbij</button></td>
        </tr>
    )
}

export default Request
