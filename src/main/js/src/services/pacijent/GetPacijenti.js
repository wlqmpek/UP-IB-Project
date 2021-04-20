import React, {useState, useEffect} from 'react';
import axious from 'axios';
import PrikazSvihPacijenata from '../../components/pacijent/PrikazSvihPacijenata';


const GetPacijenti = (props) => {

    const [pacijenti, izmenaPacijenata] = useState([]);

    useEffect(() => {
        pribaviSvePacijente();
    }, []);

    const pribaviSvePacijente = () => {
        axious.get("http://localhost:8080/KlinickiCentar/Pacijenti").then((response) => {
            const sviPacijenti = response.data;
            izmenaPacijenata(sviPacijenti);
        })
    };
    
    console.log("Print 1" + pacijenti);

    return (
        <PrikazSvihPacijenata pacijenti={pacijenti}/>
    )
}

export default GetPacijenti;
