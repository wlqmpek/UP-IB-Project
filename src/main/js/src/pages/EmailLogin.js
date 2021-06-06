import React, { useEffect, useState } from "react";
import { AuthenticationService } from "../services/AuthenticationService";
import { useParams } from "react-router";

const EmailLogin = () => {

    const { token } = useParams();

    const [greska, setGreska] = useState("");

    useEffect(() => {
        emailLogin(token)
    }, [token])

    async function emailLogin(token){
        try {
            await AuthenticationService.emailLogin(token);
            setGreska("Redirekcija...")
        } catch (error) {
            setGreska("Token je istekao")
            console.log("Greska")
            console.log(error)
        }
    };

    return (
        <div><h3>{greska}</h3></div>
    );

}

export default EmailLogin;