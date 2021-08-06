import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import { Col, Container, Row, Modal, Button, Form, Table } from "react-bootstrap";
import { ClinicsService } from "../../services/ClinicsService";
import { ParametriPretrageService } from "../../services/ParametriPretrageService";

const ViewAllClinics = () => {

    const [klinike, setKlinike] = useState([]);

    useEffect(() => {
        pribaviSveKlinike();
    }, []);

    const history = useHistory();

    async function pribaviSveKlinike() {
        try {
            const response = await ClinicsService.getClinics();
            // console.log(response.data);
            setKlinike(response.data);
        } catch (error) {
            console.error(`Greska: ${error}`);
        }
    }

    function prikaziTermineKlinike(parametri) {
        parametri = {...parametri, odDatuma: new Date().toISOString().split('T')[0]};
        ParametriPretrageService.setParametri(parametri);
        history.push(`/prikaz-termina-klinike`);
    }

    return(
        <>
            <h1 className="text-center">Prikaz Svih Klinika</h1>
            <Table striped bordered hover style={{marginTop: 50, marginBottom: 50}}>
                <thead className="thead-dark">
                <tr>
                    <th>Naziv</th>
                    <th>Adresa</th>
                    <th>Opis</th>
                    <th>Ocena</th>
                    <th>Pregledi</th>
                </tr>
                </thead>
                <tbody>
                {klinike.map((klinika) => {
                    return (
                        <tr key={klinika.idKlinike}>
                            <td>{klinika.naziv}</td>
                            <td>{klinika.adresa}</td>
                            <td>{klinika.opis}</td>
                            <td>{klinika.ocena}</td>
                            <td>
                                <Button
                                    className="btn btn-danger"
                                    block
                                    onClick={() => prikaziTermineKlinike({idKlinike:klinika.idKlinike})}
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

export default ViewAllClinics;