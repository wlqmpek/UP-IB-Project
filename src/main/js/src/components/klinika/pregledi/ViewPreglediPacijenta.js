import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import { Col, Container, Row, Modal, Button, Table } from "react-bootstrap";
import { AuthenticationService } from "../../../services/AuthenticationService";
import PreglediService from "../../../services/PreglediService";
import LekarService from "../../../services/LekarService";
import {MedicinskaSestraService} from "../../../services/MedicinskaSestraService";
import ClinicsService from "../../../services/ClinicsService";

const ViewPreglediPacijenta = () => {

    const [pregledi, setPregledi] = useState([]);
    const [lekari, setLekari] = useState([]);
    const [sestre, setSestre] = useState([]);
    const [klinike, setKlinike] = useState([]);

    const id = AuthenticationService.getId();

    useEffect(() => {
        getPregledi();
    }, [id]);

    useEffect(() => {
        getLekari();
        getSestre();
        getKlinike();
    }, [pregledi]);

    useEffect(() => {
        console.log(lekari);
    }, [lekari]);

    async function getPregledi() {
        try {
            const response = await PreglediService.getPreglediPacijenta();
            setPregledi(response.data);
        } catch (e) {
            console.log(`Greska u View pregledi pacijenta ${e}`);
        }
    }

    async function getLekari() {
        let arr = [];
        await Promise.all(pregledi.map(async (pregled) => {
            try {
                const response = await LekarService.getLekar(pregled.idLekara);
                arr = arr.concat(response.data);
                setLekari(arr);
            } catch (error) {
                console.error(`Greska: ${error}`);
            }
        }));
        setLekari(arr);
    }

    async function getSestre() {
        let arr = [];
        await Promise.all(pregledi.map(async (pregled) => {
            try {
                const response = await MedicinskaSestraService.getMSestra(pregled.idMedicinskeSestre);
                arr = arr.concat(response.data);
                setSestre(arr);
            } catch (error) {
                console.error(`Greska: ${error}`);
            }
        }))
    }

    async function getKlinike() {
        let arr = [];
        await Promise.all(pregledi.map(async (pregled) => {
            try {
                const response = await ClinicsService.getClinicById(pregled.idKlinike);
                arr = arr.concat(response.data);
                setKlinike(arr);
            } catch (error) {
                console.error(`Greska: ${error}`);
            }
        }))
    }

    function vratiNazivKlinike(idKlinike) {
        var ret = "";
        if(typeof klinike.find(klinika => idKlinike === klinika.idKlinike) != "undefined") {
            ret = klinike.find(klinika => idKlinike === klinika.idKlinike).naziv;
        }
        return ret;
    }

    function vratiNazivLekara(idLekara) {
        var ret = "";
        if(typeof lekari.find(lekar => idLekara === lekar.idKorisnika) != "undefined") {
            ret = lekari.find(lekar => idLekara === lekar.idKorisnika).imeKorisnika + " " + lekari.find(lekar => idLekara === lekar.idKorisnika).prezimeKorisnika;
        }
        return ret;
    }

    function vratiNazivSestre(idSestre) {
        var ret = "";
        if(typeof sestre.find(sestra => idSestre === sestra.idKorisnika) != "undefined") {
            ret = sestre.find(sestra => idSestre === sestra.idKorisnika).imeKorisnika + " " + sestre.find(sestra => idSestre === sestra.idKorisnika).prezimeKorisnika;
        }
        return ret;
    }

    return(
        <>
            <h1 className="text-center" style={{marginTop: 25}}>Vaši Pregledi</h1>
            <Table striped bordered hover style={{marginTop: 30, marginBottom: 50}}>
                <thead className="thead-dark">
                <tr>
                    <th>Klinika</th>
                    <th>Datum</th>
                    <th>Dijagnoza</th>
                    <th>Opis</th>
                    <th>Lekar</th>
                    <th>Sestra</th>
                    <th>Cena</th>
                    <th>Popust</th>
                </tr>
                </thead>
                <tbody>
                {pregledi.map((pregled) => {
                    return(
                        <tr key={pregled.idPregleda}>
                            <td>{vratiNazivKlinike(pregled.idKlinike)}</td>
                            <td>{new Date(pregled.pocetakTermina).toLocaleDateString('en-GB', {day: '2-digit', month: '2-digit', year: 'numeric'})}</td>
                            <td>{pregled.dijagnoza}</td>
                            <td>{pregled.opis}</td>
                            <td>{vratiNazivLekara(pregled.idLekara)}</td>
                            <td>{vratiNazivSestre(pregled.idMedicinskeSestre)}</td>
                            <td>{pregled.cena}</td>
                            <td>{pregled.popust + " %"}</td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>
        </>
    );
}

export default ViewPreglediPacijenta;