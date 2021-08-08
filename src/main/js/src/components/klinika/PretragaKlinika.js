import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import { Col, Container, Row, Modal, Button, Form, Table } from "react-bootstrap";
import { AuthenticationService } from "../../services/AuthenticationService";
import { ClinicsService } from "../../services/ClinicsService";
import DatePicker from "react-datepicker";
import {ParametriPretrageService} from "../../services/ParametriPretrageService";
// import { useParametriPretrage } from "../context/ParametriPretrageContext";


const PretragaKlinika = () => {

    const[parametriPretrage, setParametriPretrage] = useState({
        datum: new Date(),
        adresa: "",
        ocena: 0
    });

    const [klinike, setKlinike] = useState([]);

    useEffect(() => {
        pretrazi();
        ParametriPretrageService.setParametri(parametriPretrage);
    }, [parametriPretrage]);


    const history = useHistory();

    async function pretrazi() {
        try {
            const parametri = {...parametriPretrage, datum: parametriPretrage.datum.toISOString().split('T')[0]};
            const response = await ClinicsService.pretragaKlinika(parametri);
            // console.log(response.data);
            setKlinike(response.data);
        } catch (error) {
            console.error(`Greska: ${error}`);
        }
    }

    function formatDate(selectedDate) {
        return new Date(selectedDate.getTime() - selectedDate.getTimezoneOffset() * -60000);
    }

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setParametriPretrage({...parametriPretrage, [name]: val});
    }

    const handleSelectInputChange = (name) => (event) => {
        const val = parseInt(event.target.value);
        setParametriPretrage({...parametriPretrage, [name]: val});
    }

    function prikaziKliniku() {
        history.push(`/prikaz-lekara`);
    }

    return (
        <>
            <Form>
                <h1 className="text-center">Pretraga Klinika</h1>
                <Form.Group as={Row} className="justify-content-md-center">
                    <Form.Label column sm="2" className="">Datum Pregleda</Form.Label>
                    <Col sm="3">
                        <DatePicker
                            placeholderText="Izaberite dan pregleda."
                            // dateFormat="dd/MM/y"
                            selected={parametriPretrage.datum}
                            onChange={date => setParametriPretrage({...parametriPretrage, datum: formatDate(date)})}
                            dateFormat="dd/MM/Y"
                        />
                    </Col>

                </Form.Group>
                <Form.Group as={Row} className="justify-content-md-center">
                    <Form.Label column sm="2">Adresa Klinike</Form.Label>
                    <Col sm="3">
                        <Form.Control
                            type="text"
                            value={parametriPretrage.adresa}
                            onChange={handleFormInputChange("adresa")}>
                        </Form.Control>
                    </Col>
                </Form.Group>
                <Form.Group as={Row} className="justify-content-md-center">
                    <Form.Label column sm="2">Minimalna Ocena</Form.Label>
                    <Col sm="3">
                        <Form.Control
                            onChange={handleSelectInputChange("ocena")}
                            required
                            value={parametriPretrage.ocena}
                            as="select"
                        >
                            <option>0</option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                        </Form.Control>
                    </Col>
                </Form.Group>
            </Form>
            <Table striped bordered hover style={{marginTop: 50, marginBottom: 50}}>
                <thead className="thead-dark">
                <tr>
                    <th>Naziv</th>
                    <th>Ocena</th>
                    <th>Adresa</th>
                    <th>Pregledi</th>
                    <th>Prikazi Lekare</th>
                </tr>
                </thead>
                <tbody>
                {klinike.map((klinika) => {
                    return (
                        <tr key={klinika.idKlinike}>
                            <td>{klinika.naziv}</td>
                            <td>{klinika.adresa}</td>
                            <td>{klinika.ocena}</td>

                            <td>
                                <Button
                                    className="btn btn-danger"
                                    block
                                    onClick={() => {}}
                                    // onClick={() => props.deleteArticle(article.articleId)}
                                >
                                    Delete Article
                                </Button>
                            </td>
                            <td>
                                <Button
                                    className="btn btn-danger"
                                    block
                                    onClick={() => prikaziKliniku()}
                                    // onClick={() => props.deleteArticle(article.articleId)}
                                >
                                    Prikazi Lekare/Termine
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

export default PretragaKlinika;