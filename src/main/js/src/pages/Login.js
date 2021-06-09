import React, {useState} from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { AuthenticationService } from "../services/AuthenticationService";

const Login = () => {
    const [credentials, setCredentials] = useState({
        emailKorisnika: "",
        lozinkaKorisnika: "",
    });

    const [greska, setGreska] = useState("");

    // Funkcija koja prima naziv polja koje ce se azurirati, a potom i dogadjaj koji je doveo do tog azuriranja
    // Iz dogadjaja je moguce izvuci novu vrednost polja
    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        // ... - Destructuring assigment - omogucuje raspakivanje vrednosti objekta ili nizova
        /* setCredentials ce zameniti stanje novim objektom koji uzima vrednost iz prethodnog stanja kredencijala
        ali sa azuriranom vrednoscu [name] polja. */
        setGreska("");
        setCredentials( { ...credentials, [name]: val });
    };

    // Asinhrono izvrsavanje koda znaci da nije neophodno blokirati izvrsavanje i cekati da se funkcija zavrsi
    // Koriste se za operacije koje dugo traju - slanje zahteva ka nekom serveru, citanje sa diska ...
    // Moraju da sadrze await keywoard i vracaju Promise objekat koji nakon sto stigne nastavlja se sa obradom.
    const login = async () => {
        try {
            await AuthenticationService.login(credentials);
        } catch (error) {
            console.log("Greska")
            console.log(error)
            if(error.response.status === 403)
            console.log(error.response.status);
            setGreska("Neispravni podaci!");
            throw error;
        }
    };

    const emailLoginRequest = async () => {
        try {
            if (credentials.emailKorisnika === "") {
                setGreska("Unestie mejl adresu!");
            }
            else if (credentials.emailKorisnika.includes("@gmail.com") != true) {
                setGreska("Unestie validnu mejl adresu!");
            }
            else {
                await AuthenticationService.emailLoginRequest(credentials);
                setGreska("Zahtev je uspesno poslat!");
            }
        } catch (error) {
            console.log("Greska")
            console.log(error)
            
            if (error.response.status === 404)
                setGreska("Nepostojeca mejl adresa!");
            else {
                setGreska("Neispravni podaci!");
            }
            throw error;
        }
    };

    // Return vraća JSX (JavaScript XML) - notaciju kroz koju je moguće elemente unutar React-a
    // Ovi elementi ujedno mogu da sadrže deklaraciju UI komponeti i poslovnu logiku
    // JSX je samo notacije te komponente koje se vraćaju ne moraju da budu samo vezane za HTML tagove
    // Koristi se i u drugim projektima poput React Native

    // JSX nije nužno koristi - ovo je samo deklarativan zapis funkcija koje će React izvesti
    // https://reactjs.org/docs/react-without-jsx.html
    return (
        <Container>
            <Row>
                <Col md={{ span: 6, offset: 3}} style={{textAlign : "center"}}>
                    {(greska !== "") ? (<div className="greska">{greska}</div> ) : ""}
                    <Form>
                        <Form.Group>
                            <Form.Label>Email</Form.Label>
                            <Form.Control
                                type="text"
                                name="emailKorisnika"
                                value={credentials.emailKorisnika}
                                onChange={handleFormInputChange("emailKorisnika")}>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Lozinka</Form.Label>
                            <Form.Control
                                type="password"
                                name="lozinkaKorisnika"
                                value={credentials.lozinkaKorisnika}
                                onChange={handleFormInputChange("lozinkaKorisnika")}>
                            </Form.Control>
                        </Form.Group>
                        <Button variant="success" onClick={login}>Log in</Button>
                        <Button variant="btn-info" onClick={emailLoginRequest}>Log in via email</Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default Login;

