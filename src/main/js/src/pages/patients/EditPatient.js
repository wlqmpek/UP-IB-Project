import React, {useEffect, useState} from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { AuthenticationService } from "../../services/AuthenticationService";
import { TokenService } from "../../services/TokenService";
import {PatientService} from "../../services/pacijent/PatientService";
import { useParams } from "react-router";

const  EditPatientDetails = () => {
    const [patientDetails, setPatientDetails] = useState({
        imeKorisnika: "",
        prezimeKorisnika: "",
        lozinkaKorisnika: "",
        ponovljenaLozinkaKorisnika: ""
    });

    const[showSuccessAlert, setShowSuccessAlert] = useState(false);

    // useParams Hook iz React Routera - preko nje je moguće dobiti paramet koji se zahteva na ruti, poput ID-ja
    const { id } = useParams();

    useEffect(() => {
        fetchPatient(id);
    }, [id]);

    const [inputError, setInputError] = useState("");

    async function fetchPatient(id) {
        console.log("Fetchovanje pacijenata");
        try {
            const response = await PatientService.getPatient(id);
            console.log("Fetch Patient " + response.data)
            setPatientDetails(response.data)
        } catch (error) {
            console.log(`Greška prilikom dobavljanja pacijenta`)
        }
    }

    async function editPatient() {
        try {
            await PatientService.editPatient(id, patientDetails);
        } catch (error) {
            console.error(`Greška prilikom аžuriranja stanja korisnika: ${error}`);
        }
    }

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setInputError("");
        setPatientDetails({ ...patientDetails, [name]: val });
    };

    const update = async () => {
        if((!TokenService.didAccessTokenExpire()) && (AuthenticationService.getRole() == "ROLE_ADMINISTRATOR" || AuthenticationService.getRole() == "ROLE_PACIJENT")){
            // Radi se u slucaju da token nije isttekao i da je rola admin ili pacijent.
            console.log("Uspesno 1");
            editPatient()
        } else {
            console.log("Neuspesno");
        }
    };

    return (
        <Container>
            <Row>
                <Col md={{ span: 6, offset: 3}} style={{textAlign : "center"}}>
                    {(inputError !== "") ? (<div className="inputError">{inputError}</div> ) : ""}
                    <Form>
                        <Form.Group>
                            <Form.Label>Ime</Form.Label>
                            <Form.Control
                            type="text"
                            name="imeKorisnika"
                            value={patientDetails.imeKorisnika}
                            onChange={handleFormInputChange("imeKorisnika")}>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Prezime</Form.Label>
                            <Form.Control
                                type="text"
                                name="prezimeKorisnika"
                                value={patientDetails.prezimeKorisnika}
                                onChange={handleFormInputChange("prezimeKorisnika")}>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Lozinka</Form.Label>
                            <Form.Control
                                type="password"
                                name="lozinkaKorisnika"
                                value={patientDetails.lozinkaKorisnika || ""}
                                onChange={handleFormInputChange("lozinkaKorisnika")}>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Pon.Lozinka</Form.Label>
                            <Form.Control
                                type="password"
                                name="ponovljenaLozinkaKorisnika"
                                value={patientDetails.ponovljenaLozinkaKorisnika || ""}
                                onChange={handleFormInputChange("ponovljenaLozinkaKorisnika")}>
                            </Form.Control>
                        </Form.Group>
                        <Button variant="success" onClick={update}>Update</Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default EditPatientDetails;