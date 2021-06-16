import React, {useEffect, useState} from 'react'
import { Link } from 'react-router-dom';
import LoginRegisterBT from "./LoginRegisterBT";
import Logout from "./Logout";
import {AuthenticationService} from "../services/AuthenticationService";
import HeaderLekar from "./lekar/HeaderLekar";
import HeaderPacijent from "./pacijent/HeaderPacijent";
import HeaderGuest from "./HeaderGuest";

const HeaderComponent = () => {

    const [role, setRole] = useState("")

    const jwt = localStorage.getItem("accessToken");

    useEffect(()=>{
        getRoleFromToken()
    })

    const logout = () => {
        if(jwt === null){
            return <LoginRegisterBT/>
        }else{
            return <Logout/>
        }
    }

    function getRoleFromToken() {
        if(AuthenticationService.getRole() !== null){
            setRole(AuthenticationService.getRole)
        }
    }
    const usersHeader = () => {

        console.log(role)
        if(role === "ROLE_LEKAR"){
           return <HeaderLekar/>
        }else if(role === "ROLE_ADMINISTRATOR"){

        }else if(role === "ROLE_MEDICINKSA_SESTRA"){

        }else if(role === "ROLE_PACIJENT"){
            return <HeaderPacijent/>
        }else if (role === ""){
            return <HeaderGuest/>
        }
    }

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
                                {usersHeader()}
                                {logout()}
                            </div>
                        </div>
                    </nav>
                </header>
        )
}
export default HeaderComponent