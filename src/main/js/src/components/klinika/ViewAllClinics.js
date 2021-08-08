import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import { Col, Container, Row, Modal, Button, Form, Table } from "react-bootstrap";
import { ClinicsService } from "../../services/ClinicsService";
import { ParametriPretrageService } from "../../services/ParametriPretrageService";
import useSortableData from "../SortTable";

const ViewAllClinics = () => {

    const [klinike, setKlinike] = useState([]);

    useEffect(() => {
        pribaviSveKlinike();
    }, []);

    const styleButton = {
        border: "0",
        backgroundColor: "#343a40",
        borderRadius: "none",
        fontFamily: "inherit",
        fontSize: "inherit",
        color: "white"
    }

    const history = useHistory();

    const {items, requestSort, sortConfig} = useSortableData(klinike)

    const getClassNameFor = (name) => {
        if(!sortConfig){
            return;
        }
        return sortConfig.key === name ? sortConfig.direction : undefined
    }

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
            <Table striped bordered hover style={{marginTop: 50, marginBottom: 50, textAlign: "center"}}>
                <thead className="thead-dark">
                <tr>
                    <th>
                        <button
                            type="button"
                            style={styleButton}
                            onClick={() => requestSort("naziv")}
                            className={getClassNameFor("naziv")}
                        >
                            Naziv
                        </button>
                    </th>
                    <th>
                        <button
                            type="button"
                            style={styleButton}
                            onClick={() => requestSort("adresa")}
                            className={getClassNameFor("adresa")}
                        >
                            Adresa Klinike
                        </button>
                    </th>
                    <th>Opis</th>
                    <th><button
                        type="button"
                        style={styleButton}
                        onClick={() => requestSort("ocena")}
                        className={getClassNameFor("ocena")}
                    >
                        Ocena
                    </button></th>
                    <th>Pregledi</th>
                </tr>
                </thead>
                <tbody>
                {items.map((klinika, index) => {
                    return (
                        <tr key={klinika.idKlinike}>
                            <td style={{verticalAlign: "middle"}}>{klinika.naziv}</td>
                            <td style={{verticalAlign: "middle"}}>{klinika.adresa}</td>
                            <td style={{verticalAlign: "middle"}}>{klinika.opis}</td>
                            <td style={{verticalAlign: "middle"}}>{klinika.ocena}</td>
                            <td style={{verticalAlign: "middle"}}   >
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