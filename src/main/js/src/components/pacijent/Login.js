import { useState} from "react";
import axios from 'axios';

const Login = () => {

    const [emailKorisnika, setEmailKorisnika] = useState("");
    const [lozinkaKorisnika, setLozinkaKorisnika] = useState("");

    function PacijentLogin() {

        console.log("Pacijent Login");

        const pacijent = {
            emailKorisnika: emailKorisnika,
            lozinkaKorisnika: lozinkaKorisnika
        }

        const json = JSON.stringify(pacijent);
        console.log("Moj objekat " + json);

        axios.post('http://localhost:8080/KlinickiCentar/Pacijenti/login', json, {
            headers: {
                // Overwrite Axios's automatically set Content-Type
                'Content-Type': 'application/json'
            }
        }).then(response => {
            alert("Ulogovan korisnik je" + JSON.stringify(response.data))
        }).catch(error => {
            if (error.response) {
                // client received an error response (5xx, 4xx)
            } else if (error.request) {
                // client never received a response, or request never left
            } else {
                // anything else
            }
        });

        // const onLogIn = async (pacijent) => {
        //     console.log("Acious")
        //     axious.post("http://localhost:8080/KlinickiCentar/Pacijenti/login", pacijent).then(response => console.log(response));
        // }

        
    }

    const onSubmit = (e) => {
        e.preventDefault();

        if(!emailKorisnika || !lozinkaKorisnika) {
            alert("Molim vas unesite lozinku i email!")
            return
        }

        console.log("Submit stisnuto");

        PacijentLogin()

        
    }

    return (
        <div style={{ margin: "50px" }}>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 style={{ margin: "10px",textDecoration: "underline" }} className="text-center">Prijava</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> Email: </label>
                                        <input type="text" value={emailKorisnika} onChange={e => setEmailKorisnika(e.target.value)} placeholder="Email" className="form-control"></input>
                                        <br />
                                        <label> Password: </label>
                                        <input type="password" value={lozinkaKorisnika} onChange={e => setLozinkaKorisnika(e.target.value)} placeholder="Lozinka" className="form-control"></input>
                                    </div>
                                    <button type='submit' className='btn btn-primary' onClick={onSubmit}>Prijavi se</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
    )
}

export default Login