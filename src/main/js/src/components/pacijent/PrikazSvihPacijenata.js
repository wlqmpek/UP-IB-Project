import React from 'react';


const PrikazSvihPacijenata = (props) => {

    console.log("Print 2" + props.pacijenti);

    if(props.pacijenti.length > 0) {
        console.log("Print 3" + props.pacijenti);
        var pacijentiRedovi = props.pacijenti.map((pacijent) => {
            return(
                <tr key={pacijent.idKorisnika}>
                    <td>{pacijent.idKorisnika}</td>
                    <td>{pacijent.imeKorisnika}</td>
                    <td>{pacijent.prezimeKorisnika}</td>
                    <td>{pacijent.emailKorisnika}</td>
                    <td>{pacijent.statusKorisnika}</td>
                    <th><button>Izmeni</button></th>
                    <th><button>Obrisi</button></th>
                </tr>
            );
        });
    
        return (
            <table className="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ime</th>
                        <th>Prezime</th>
                        <th>Email</th>
                        <th>StatusKorisnika</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {pacijentiRedovi}
                </tbody>
            </table>
        )
    } else {
        return(<table className="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ime</th>
                    <th>Prezime</th>
                    <th>Email</th>
                    <th>StatusKorisnika</th>
                    
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>No data</td>
                </tr>
            </tbody>
        </table>
    );
    }
}

export default PrikazSvihPacijenata;
