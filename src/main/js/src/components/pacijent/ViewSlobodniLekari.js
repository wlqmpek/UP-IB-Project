import React, { useEffect, useState, useContext } from "react";
import { useHistory, useParams } from "react-router";
import { Col, Container, Row, Modal, Button, Form, Table } from "react-bootstrap";
import { AuthenticationService } from "../../services/AuthenticationService";
import {ParametriPretrageService} from "../../services/ParametriPretrageService";
import { LekarService } from "../../services/LekarService";

const ViewSlobodniLekari = () => {


    const [lekari, setLekari] = useState([]);
    const [termini, listaTermina] = useState([]);
    const [parametriPretrage, setParametriPretrage] = useState({
        termin:ParametriPretrageService.getParametri().datum
    });

    const history = useHistory();

    const { id } = useParams();

    useEffect(() => {
        pribaviLekare();
    }, []);

    useEffect(() => {

    }, []);

    async function pribaviLekare() {
        try {
           const response = await LekarService.getLekariUTerminu(parametriPretrage);
           setLekari(response.data);
        } catch (error) {
            console.error(`Greska: ${error}`);
        }
    }

    function izaberiPregled(idPregleda) {
        history.push(`/zakazivanje-pregleda/${idPregleda}`);
    }



    return(
        <>
            <h1 className="text-center">Odabir Termina</h1>
            <Table striped bordered hover style={{marginTop: 50, marginBottom: 50}}>
                <thead className="thead-dark">
                    <tr>
                        <th>Ime</th>
                        <th>Prezime</th>
                        <th>Ocena</th>
                        <th>Termini Pregleda</th>
                    </tr>
                </thead>
                <tbody>
                {lekari.map((lekar) => {
                    return (
                        <tr key={lekar.idKorisnika}>
                            <td>{lekar.imeKorisnika}</td>
                            <td>{lekar.prezimeKorisnika}</td>
                            <td>{lekar.ocena}</td>
                            <td>
                                <Table striped bordered hover style={{marginTop:1, marginBottom: 1}}>
                                    <thead className="thead-dark">
                                    <tr>
                                        <th>Pocetak Termina</th>
                                        <th>Kraj Termina</th>
                                        <th>Zakazivanje Termina</th>
                                    </tr>

                                    </thead>
                                    <tbody>
                                    {lekar.pregledi.map((pregled) => {
                                        return(
                                            <tr key={pregled.idPregleda}>
                                                <td>{new Date(pregled.pocetakTermina).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit',  hour12: false })}</td>
                                                <td>{new Date(pregled.krajTermina).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit',  hour12: false })}</td>
                                                <td>
                                                    <Button
                                                        className="btn btn-link"
                                                        onClick={() => izaberiPregled(pregled.idPregleda)}
                                                    >
                                                        Odaberi Termin
                                                    </Button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                    </tbody>
                                </Table>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>
        </>
    );
}

export default ViewSlobodniLekari;