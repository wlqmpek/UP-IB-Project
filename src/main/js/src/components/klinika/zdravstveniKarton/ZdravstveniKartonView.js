import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import { Col, Container, Row, Modal, Button, FormGroup, FormControl, Form, FormLabel } from "react-bootstrap";
import { ZdravstveniKartonService } from "../../../services/ZdravstveniKartonService";

const ZdravstveniKartonView = () => {
    const [zdravstveniKarton, setZdravstveniKarton] = useState({});
    useEffect(() => {
        getZdravstveniKarton();
    }, []);

    const { id } = useParams();

    async function getZdravstveniKarton() {
        try {
            const response = await ZdravstveniKartonService.getZdravstveniKarton(id);
            setZdravstveniKarton(response.data);
            console.log(response.data);
        } catch (e) {
            console.log("Error in ZdravstveniKartonView " + e);
        }
    }

    return(
        <>
            <Form>
                <h1 className="text-center">Zdravstveni Karton</h1>

                <FormGroup as={Row} className="justify-content-md-center">
                    <FormLabel className="border-top border-bottom border-left border-dark" column xs={2}>Visina</FormLabel>
                    <Col xs={6}>
                        <FormControl className="text-center border-bottom border-right border-dark"  plaintext readOnly defaultValue={zdravstveniKarton.visina} />
                    </Col>
                </FormGroup>
                <FormGroup as={Row} className="justify-content-md-center">
                    <FormLabel className="border-top border-bottom border-left border-dark" column xs={2}>Tezina</FormLabel>
                    <Col xs={6}>
                        <FormControl className="text-center border-bottom border-right border-dark" plaintext readOnly defaultValue={zdravstveniKarton.tezina} />
                    </Col>
                </FormGroup>
                <FormGroup as={Row} className="justify-content-md-center">
                    <FormLabel className="border-top border-bottom border-left border-dark" column xs={2}>Krvna Grupa</FormLabel>
                    <Col xs={6}>
                        <FormControl className="text-center border-bottom border-right border-dark" plaintext readOnly defaultValue={zdravstveniKarton.krvnaGrupa} />
                    </Col>
                </FormGroup>
                <FormGroup as={Row} className="justify-content-md-center">
                    <FormLabel className="border-top border-bottom border-left border-dark" column xs={2}>Dioptrija</FormLabel>
                    <Col xs={6}>
                        <FormControl className="text-center border-bottom border-right border-dark" plaintext readOnly defaultValue={zdravstveniKarton.dioptrija} />
                    </Col>
                </FormGroup>
                <FormGroup as={Row} className="justify-content-md-center">
                    <FormLabel className="border-top border-bottom border-left border-dark" column xs={2}>Alergije</FormLabel>
                    <Col xs={6}>
                        <FormControl className="text-center border-bottom border-right border-dark" plaintext readOnly as={"textarea"} value={zdravstveniKarton.alergije}></FormControl>
                    </Col>
                </FormGroup>
                {/*<Row>*/}
                {/*    <Col md={{ span: 8, offset: 2 }} style={{ textAlign: "center" }}>*/}
                {/*        <h1>Zdravstveni Karton</h1>*/}
                {/*        <FormGroup className="mb-3">*/}
                {/*            <FormGroup.Text id="basic-addon1">Visina</FormGroup.Text>*/}
                {/*            <FormControl*/}
                {/*                aria-label="Username"*/}
                {/*                aria-describedby="basic-addon1"*/}
                {/*                plaintext readOnly defaultValue={zdravstveniKarton.visina}*/}
                {/*            />*/}
                {/*        </FormGroup>*/}
                {/*    </Col>*/}
                {/*    <Col md={{ span: 8, offset: 2 }} style={{ textAlign: "center" }}>*/}
                {/*        <FormGroup className="mb-3">*/}
                {/*            <FormGroup.Text id="basic-addon1">Tezina</FormGroup.Text>*/}
                {/*            <FormControl*/}
                {/*                placeholder={zdravstveniKarton.tezina}*/}
                {/*                aria-label="Username"*/}
                {/*                aria-describedby="basic-addon1"*/}
                {/*                disabled*/}
                {/*            />*/}
                {/*        </FormGroup>*/}
                {/*    </Col>*/}
                {/*    <Col md={{ span: 8, offset: 2 }} style={{ textAlign: "center" }}>*/}
                {/*        <FormGroup className="mb-3">*/}
                {/*            <FormGroup.Text id="basic-addon1">Krvna Grupa</FormGroup.Text>*/}
                {/*            <FormControl*/}
                {/*                placeholder={zdravstveniKarton.visina}*/}
                {/*                aria-label="Username"*/}
                {/*                aria-describedby="basic-addon1"*/}
                {/*                disabled*/}
                {/*            />*/}
                {/*        </FormGroup>*/}
                {/*    </Col>*/}
                {/*    <Col md={{ span: 8, offset: 2 }} style={{ textAlign: "center" }}>*/}
                {/*        <FormGroup className="mb-3">*/}
                {/*            <FormGroup.Text id="basic-addon1">Dioptrija</FormGroup.Text>*/}
                {/*            <FormControl*/}
                {/*                placeholder={zdravstveniKarton.dioptrija}*/}
                {/*                aria-label="Username"*/}
                {/*                aria-describedby="basic-addon1"*/}
                {/*                disabled*/}
                {/*            />*/}
                {/*        </FormGroup>*/}
                {/*    </Col>*/}
                {/*    <Col md={{ span: 8, offset: 2 }} style={{ textAlign: "center" }}>*/}
                {/*        <FormGroup className="mb-3">*/}
                {/*            <FormGroup.Text id="basic-addon1">Alergije</FormGroup.Text>*/}
                {/*            <FormControl*/}
                {/*                placeholder={zdravstveniKarton.alergije}*/}
                {/*                aria-label="Username"*/}
                {/*                aria-describedby="basic-addon1"*/}
                {/*                disabled*/}
                {/*                as="textarea"*/}
                {/*                rows={3}*/}
                {/*            />*/}

                {/*        </FormGroup>*/}
                {/*    </Col>*/}
                {/*</Row>*/}
            </Form>
        </>
    );
}

export default ZdravstveniKartonView;
