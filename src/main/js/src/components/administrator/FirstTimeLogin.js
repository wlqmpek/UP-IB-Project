import React, { Component } from "react"
import AdminService from "../../services/AdminService";
import validator from 'validator';

class FirstTimeLoginComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            lozinka: '',
            ponovljenaLozinka: '',
            vrstaAdministratora: 'KLINICKOG_CENTRA'
        }
        this.changeLozinkaHandler = this.changeLozinkaHandler.bind(this);
        this.changePonovljenaLozinkaHandler = this.changePonovljenaLozinkaHandler.bind(this);
    }

    changeLozinkaHandler = (event) => {
        this.setState({ lozinka: event.target.value });
    }

    changePonovljenaLozinkaHandler = (event) => {
        this.setState({ ponovljenaLozinka: event.target.value });
    }

    componentDidMount() {
        AdminService.getAdmin(this.state.id).then(response => {
            const admin = response.data;

            this.setState({
                ime: admin.imeKorisnika,
                prezime: admin.prezimeKorisnika,
                email: admin.emailKorisnika,
                vrstaAdministratora: admin.vrstaAdministratora
            });
        });
    }


    updateAdmin = (event) => {
        event.preventDefault();

        // verifikacija podataka
        if (!this.state.lozinka || !this.state.ponovljenaLozinka) {
            alert('Oba polja su obavezna.')
            return
        }

        if (this.state.lozinka.valueOf() !== this.state.ponovljenaLozinka.valueOf()) {
            alert('Ponovljena lozinka nije ista kao uneta lozinka')
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

        AdminService.editAdmin(this.state.id, admin).then(response => {
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
                            <h3 style={{ margin: "10px" }} className="text-center">Kreiranje lozinke</h3>
                            <div className="card-body">
                                <form>
                                    <div className='form-group'>
                                        <label>Nova lozinka: </label>
                                        <input type='password' value={this.state.lozinka} onChange={this.changeLozinkaHandler} placeholder='Lozinka' className='form-control' />
                                        <br />
                                        <label>Ponovljena lozinka: </label>
                                        <input type='password' value={this.state.ponovljenaLozinka} onChange={this.changePonovljenaLozinkaHandler} placeholder='Ponovljena lozinka' className='form-control' />
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateAdmin}>Potvrda</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }

}

export default FirstTimeLoginComponent;