import React, { Component } from "react"
import AdminService from "../../services/AdminService";
import validator from 'validator';

class AddAdminComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            ime: '',
            prezime: '',
            email: '',
            lozinka: '',
            ponovljenaLozinka: '',
            vrstaAdministratora: 'KLINICKOG_CENTRA'
        }

        this.changeImeHandler = this.changeImeHandler.bind(this);
        this.changePrezimeHandler = this.changePrezimeHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changeLozinkaHandler = this.changeLozinkaHandler.bind(this);
        this.changePonovljenaLozinkaHandler = this.changePonovljenaLozinkaHandler.bind(this);
    }


    changeImeHandler = (event) => {
        this.setState({ ime: event.target.value });
    }

    changePrezimeHandler = (event) => {
        this.setState({ prezime: event.target.value });
    }

    changeEmailHandler = (event) => {
        this.setState({ email: event.target.value });
    }

    changeLozinkaHandler = (event) => {
        this.setState({ lozinka: event.target.value });
    }

    changePonovljenaLozinkaHandler = (event) => {
        this.setState({ ponovljenaLozinka: event.target.value });
    }


    saveAdmin = (event) => {
        event.preventDefault();

        // verifikacija podataka
        if (!this.state.ime || !this.state.prezime || !this.state.email || !this.state.lozinka || !this.state.ponovljenaLozinka) {
            alert('Sva polja su obavezna.')
            return
        }

        if (this.state.lozinka.valueOf() !== this.state.ponovljenaLozinka.valueOf()) {
            alert('Ponovljena lozinka nije ista kao uneta lozinka')
            return
        }

        if (!validator.isEmail(this.state.email)) {
            alert('Email nije validan')
            return
        }


        const admin = {
            imeKorisnika: this.state.ime,
            prezimeKorisnika: this.state.prezime,
            emailKorisnika: this.state.email,
            lozinkaKorisnika: this.state.lozinka,
            vrstaAdministratora: this.state.vrstaAdministratora
        }
        console.log(admin);

        AdminService.createAdmin(admin).then(response => {
            this.props.history.push(`/admini`);
        });

    }

    cancel() {
        this.props.history.push(`/admini`)
    }

    render() {
        return (

            <div className="card-body">
                <div className="container">
                    <div className="row">
                        <div className='card col-md-6 offset-md-3 offset-md-3'>
                            <h3 style={{ margin: "10px" }} className="text-center">Podaci o adminu</h3>
                            <div className="card-body">
                                <form>
                                    <div className='form-group'>
                                        <label>Ime: </label>
                                        <input type='text' value={this.state.ime} onChange={this.changeImeHandler} placeholder='Ime' className='form-control' />
                                        <br />
                                        <label>Prezime: </label>
                                        <input type='text' value={this.state.prezime} onChange={this.changePrezimeHandler} placeholder='Prezime' className='form-control' />
                                        <br />
                                        <label>Email: </label>
                                        <input type='email' value={this.state.email} onChange={this.changeEmailHandler} placeholder='Email' className='form-control' />
                                        <br />
                                        <label>Lozinka: </label>
                                        <input type='password' value={this.state.lozinka} onChange={this.changeLozinkaHandler} placeholder='Lozinka' className='form-control' />
                                        <br />
                                        <label>Ponovljena lozinka: </label>
                                        <input type='password' value={this.state.ponovljenaLozinka} onChange={this.changePonovljenaLozinkaHandler} placeholder='Ponovljena lozinka' className='form-control' />
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveAdmin}>Dodaj</button>
                                    <button className="btn" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Odustani</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }

}

export default AddAdminComponent;