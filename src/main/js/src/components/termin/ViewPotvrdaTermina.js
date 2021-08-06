import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import { PreglediService } from "../../services/PreglediService";
import {ParametriPretrageService} from "../../services/ParametriPretrageService";

const ViewPotvrdaTermina = () => {

    const [termin, setTermin] = useState({});
    const [uspeh, setUspeh] = useState(false);

    useEffect(() => {
        potvrdiTermin();
        console.log("Potvrdi termin");
    }, []);

    useEffect(() => {
        console.log(termin);
        setUspeh(true);
    }, [termin]);

    const {idKorisnika, idTermina} = useParams();

    const history = useHistory();

    async function potvrdiTermin() {
        try {
            const response = await PreglediService.potvrdaTermina(idKorisnika, idTermina);
            console.log("Helloooo");
            setTermin(response.data);
        } catch (error) {
            console.error(`Greska: ${error}`);
        }
    }

    return (
        <>
            <h1 className="text-center" style={{marginTop: 30}}>Potvrda Termina</h1>
            {uspeh &&
            <h1 className="text-center">{"Vas termin datuma " + new Date(termin.pocetakTermina).toLocaleDateString('en-GB', {day: '2-digit', month: '2-digit', year: 'numeric'}) + " u " + new Date(termin.pocetakTermina).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit',  hour12: false })  + " je uspesno zakazan!"} </h1>
            }
            {!uspeh &&
            <h1>{"Greska!"} </h1>
            }
        </>
    );
}

export default ViewPotvrdaTermina;