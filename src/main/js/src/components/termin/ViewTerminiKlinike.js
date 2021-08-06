import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import { Col, Container, Row, Modal, Button, Form, Table } from "react-bootstrap";
import { ClinicsService } from "../../services/ClinicsService";
import { PreglediService } from "../../services/PreglediService";
import { ParametriPretrageService } from "../../services/ParametriPretrageService";
import { LekarService } from "../../services/LekarService";

const ViewTerminiKlinike = () => {

    const [termini, setTermini] = useState([]);
    const [lekar, setLekar] = useState({});

    useEffect(() => {
        pribaviTermineKlinike();
    }, []);

    useEffect(() => {
        if (termini.length !== 0) {
            pribaviLekara(termini[0].idLekara);
        }
    }, [termini])

    const history = useHistory();

    async function pribaviTermineKlinike() {
        try {
            const response = await PreglediService.pretragaPregleda(ParametriPretrageService.getParametri());
            // console.log(response.data);
            setTermini(response.data);
        } catch (error) {
            console.error(`Greska: ${error}`);
        }
    }

    async function pribaviLekara(id) {
        try {
            const response = await LekarService.getLekar(id);
            // console.log(response.data);
            setLekar(response.data);
        } catch (error) {
            console.error(`Greska: ${error}`);
        }
    }

    function izaberiPregled(idPregleda) {
        history.push(`/zakazivanje-pregleda/${idPregleda}`);
    }

    return (
        <>
            <h1 className="text-center" style={{marginTop: 30}}>Prikaz Termina Klinika {termini.length}</h1>
            <Table striped bordered hover style={{marginTop: 50, marginBottom: 50}}>
                <thead className="thead-dark">
                <tr style={{textAlign: "center"}}>
                    <th>Datum Početka</th>
                    <th>Vreme Početka</th>
                    <th>Datum Kraja</th>
                    <th>Vreme Kraja</th>
                    <th>Lekar</th>
                    <th>Cena</th>
                    <th>Popust</th>
                    <th>Zakazi Pregled</th>
                </tr>
                </thead>
                <tbody>
                {termini.map((termin) => {
                    return(
                        <tr key={termin.idPregleda} style={{textAlign: "center"}}>
                            <td style={{verticalAlign: "middle"}}>{new Date(termin.pocetakTermina).toLocaleDateString('en-GB', {day: '2-digit', month: '2-digit', year: 'numeric'}).replace(/ /g, '-')}</td>
                            <td style={{verticalAlign: "middle"}}>{new Date(termin.pocetakTermina).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit',  hour12: false })}</td>
                            <td style={{verticalAlign: "middle"}}>{new Date(termin.krajTermina).toLocaleDateString('en-GB', {day: '2-digit', month: '2-digit', year: 'numeric'}).replace(/ /g, '-')}</td>
                            <td style={{verticalAlign: "middle"}}>{new Date(termin.krajTermina).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit',  hour12: false })}</td>
                            <td style={{verticalAlign: "middle"}}><p>{lekar.imeKorisnika + " " + lekar.prezimeKorisnika}</p></td>
                            <td style={{verticalAlign: "middle"}}>{termin.cena}</td>
                            <td style={{verticalAlign: "middle"}}>{termin.popust}</td>
                            <td style={{verticalAlign: "middle"}}>
                                <Button
                                    className="btn btn-danger"
                                    block
                                    onClick={() => izaberiPregled(termin.idPregleda)}
                                    // onClick={() => props.deleteArticle(article.articleId)}
                                >
                                    Odaberi Termin
                                </Button>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>
        </>
    );
}


export default ViewTerminiKlinike;