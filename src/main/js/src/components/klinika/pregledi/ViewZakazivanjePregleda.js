import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import { Col, Container, Row, Modal, Button, Form, Table, Card } from "react-bootstrap";
import { AuthenticationService } from "../../../services/AuthenticationService";
import { ClinicsService } from "../../../services/ClinicsService";
import { MedicinskaSestraService } from "../../../services/MedicinskaSestraService";
import { LekarService } from "../../../services/LekarService";
import { PreglediService } from "../../../services/PreglediService";
import { PacijentService } from "../../../services/PacijentService";

const ViewZakazivanjePregleda = () => {

    const [pregled, setPregled] = useState({});
    const [klinika, setKlinika] = useState({});
    const [lekar, setLekar] = useState({});
    const [medicinskaSestra, setMedicinskaSestra] = useState({});
    const [pacijent, setPacijent] = useState({});

    const history = useHistory();

    const { id } = useParams();

    useEffect(() => {
        pribaviPregled();
    }, [id]);

    useEffect(() => {
        console.log("Pregled")
        console.log(pregled)
        pribaviLekara(pregled.idLekara);
        pribaviMedicinskuSestru(pregled.idMedicinskeSestre);
        pribaviKliniku(pregled.idKlinike);
        pribaviPacijenta()
    }, [pregled]);

    async function pribaviPregled() {
        try {
            const response = await PreglediService.getPregled(id);
            setPregled(response.data);
        } catch (e) {
            console.log(`Error: ${e}`, e);
        }
    }

    async function pribaviLekara(idKorisnika) {
        try {
            const response = await LekarService.getLekar(idKorisnika);
            setLekar(response.data);
        } catch (e) {
            console.log(`Error: ${e}`, e);
        }
    }

    async function pribaviMedicinskuSestru(idKorisnika) {
        try {
            const response = await MedicinskaSestraService.getMSestra(idKorisnika);
            setMedicinskaSestra(response.data);
        } catch (e) {
            console.log(`Error: ${e}`, e);
        }
    }

    async function pribaviKliniku(idKlinike) {
        try {
            const response = await ClinicsService.getClinicById(idKlinike);
            setKlinika(response.data);
        } catch (e) {
            console.log(`Error: ${e}`, e);
        }
    }

    async function pribaviPacijenta() {
        try {
            const response = await PacijentService.getPacijent(AuthenticationService.getId());
            setPacijent(response.data);
        } catch (e) {
            console.log(`Error: ${e}`, e);
        }
    }



    return(
        <>
            <h1 style={{ marginTop: '20px', marginBottom: '40px'}} className="text-center">Pregled i Potvrda Pregleda</h1>
            <Row style={{ marginTop: '40px', marginBottom: '40px'}}>
                <Col>
                    <Card border="primary" style={{ width: '20rem', height: '12rem' }}>
                        <Card.Header className="text-center">Podaci o Lekaru</Card.Header>
                        <Card.Body>
                            <Card.Title className="text-center">{lekar.imeKorisnika + " " + lekar.prezimeKorisnika}</Card.Title>
                            <Card.Text>
                                {"Email lekara: " + lekar.emailKorisnika}
                                <br/>
                                {"Ocena lekara: " + lekar.ocena}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card border="primary" style={{ width: '20rem', height: '12rem' }}>
                        <Card.Header className="text-center">Podaci o Medicinskoj Sestri</Card.Header>
                        <Card.Body>
                            <Card.Title className="text-center">{medicinskaSestra.imeKorisnika + " " + medicinskaSestra.prezimeKorisnika}</Card.Title>
                            <Card.Text>
                                {"Email sestre: " + medicinskaSestra.emailKorisnika}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card border="primary" style={{ width: '20rem', height: '12rem' }}>
                        <Card.Header className="text-center">Podaci o Klinici</Card.Header>
                        <Card.Body>
                            <Card.Title className="text-center">{klinika.naziv}</Card.Title>
                            <Card.Text>
                                {"Adresa: " + klinika.adresa}
                                <br/>
                                {"Opis: " + klinika.opis}
                                <br/>
                                {"Ocena: " + klinika.ocena}
                                <br/>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row style={{ marginTop: '40px', marginBottom: '40px'}}>
                <Col>
                    <Card border="primary" style={{ width: '20rem', height: '12rem' }}>
                        <Card.Header className="text-center">Podaci o Pregledu</Card.Header>
                        <Card.Body>
                            <Card.Title className="text-center">{pregled.opis}</Card.Title>
                            <Card.Text>
                                {"Pocetak termina: " + new Date(pregled.pocetakTermina).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit',  hour12: false })}
                                <br/>
                                {"Kraj termina: " + new Date(pregled.krajTermina).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit',  hour12: false })}
                                <br/>
                                {"Cena termina: " + pregled.cena}
                                <br/>
                                {"Popust: " + pregled.popust + "%"}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card border="primary" style={{ width: '20rem', height: '12rem' }}>
                        <Card.Header className="text-center">Vaši Podaci</Card.Header>
                        <Card.Body>
                            <Card.Title className="text-center">{pacijent.ime + " " + pacijent.prezime}</Card.Title>                            <Card.Text>
                                {"Email: " + pacijent.email}
                                <br/>
                                {"JBZO pacijenta: " + pacijent.jbzo}
                                <br/>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col>
                    <Card border="primary" style={{ width: '20rem', height: '12rem' }}>
                        <Card.Header className="text-center">Potvrda Pregleda</Card.Header>
                        <Card.Body>
                            <Card.Text>
                                <Row>
                                    <Col>Klikom na dugme Potvrdi na vas email vam stize obavestenje o zakazanom pregledu.</Col>
                                </Row>
                            </Card.Text>
                            <Row>
                                <Col>
                                    <Button
                                        className="btn btn-danger"
                                        onClick={() => {console.log(pacijent)}}
                                        style={{margin: '2px'}}
                                    >
                                        Otkazi
                                    </Button>
                                </Col>
                                <Col>
                                    <Button
                                        className="btn btn-green"
                                        onClick={() => {console.log(pacijent)}}
                                        style={{margin: '2px'}}
                                    >
                                        Potvrdi
                                    </Button>
                                </Col>
                            </Row>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </>
    );
}

export default ViewZakazivanjePregleda;