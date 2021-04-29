import React from 'react'
import Registration from './pacijent/Registration'
import { MDBIcon } from 'mdbreact'
import { Link } from 'react-router-dom';

class HeaderComponent extends React.Component {
    render() {
        return (
                <header>
                    <nav className="navbar navbar-expand-lg navbar-dark bg-primary sticky-top" id="navbar_header">
                        <div className="container-fluid">
                            <div className="navbar-header">
                                <Link to="/" className="navbar-brand">Klinički Centar Hipokrat</Link>
                            </div>
                            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                                <span className="navbar-toggler-icon"></span>
                            </button>
                            <div className="collapse navbar-collapse" id="navbarNav">
                                <ul className="nav navbar-nav">
                                    <li className="nav-item"><Link className="nav-link" to="/">Početna</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/klinike">Klinike</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/">Cenovnik</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/admini">Administratori</Link></li>
                                </ul>
                                <ul className="navbar-nav">
                                    <li className="nav-item"><Link className="nav-link" to="/registracija"><MDBIcon icon="user-plus" />Registracija</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/pacijenti/prijava"><MDBIcon icon="sign-in-alt" />Prijava</Link></li>
                                </ul> 
                            </div>
                        </div>
                    </nav>
                </header>
        )
    }
}
export default HeaderComponent