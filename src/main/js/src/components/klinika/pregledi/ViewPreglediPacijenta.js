import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import { Col, Container, Row, Modal, Button } from "react-bootstrap";
import { AuthenticationService } from "../../../services/AuthenticationService";
import PreglediService from "../../../services/PreglediService";

const ViewPreglediPacijenta = () => {

    const [pregledi, setPregledi] = useState([]);

    const id = AuthenticationService.getId();

    useEffect(() => {
        getPregledi();
    }, [id]);

    async function getPregledi() {
        try {
            const response = await PreglediService.getPreglediPacijenta();
            setPregledi(response.data);
        } catch (e) {
            console.log(`Greska u View pregledi pacijenta ${e}`);
        }
    }

    return(
        <>
            <h1>{pregledi.length}</h1>
        </>
    );
}

export default ViewPreglediPacijenta;