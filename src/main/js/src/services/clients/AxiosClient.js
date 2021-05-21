import axios from "axios";
import { TokenService } from "../TokenService";
import { AuthenticationService } from "../AuthenticationService";

// API klijent se kreira ka specificnom endpoint-u, uz sve ono sto je uvek neophodno slati.
const AxiosClient = axios.create({
    baseURL: `${process.env.REACT_APP_CLINIC_CENTER_BACKEND_SERVER}`,
});

// Dodaj token na svaki zahtev ka backendu, ako je korisnik ulogovan.
// Nemam pojma kako funkcionice. - WLQ
AxiosClient.interceptors.request.use(function success(config) {

    const token = TokenService.getToken();
    console.log("Axious Client " + token);
    if(token) {
        if (TokenService.didTokenExpire()) {
            alert("Token je istekao");
            AuthenticationService.logout();
            return false;
        }
        config.headers["Authorization"] = "Bearer " + token;
    }
    return config;
});

// U slucaju da se sa backenda vrati forbidden, token je istekao te izloguj korisnika.
AxiosClient.interceptors.response.use(
    function success(response) {
        return response;
    },
    function failure(error) {
        const token = TokenService.getToken();
        if (token) {
            if (error.response && error.response.status === 403) {
                AuthenticationService.logout();
            }
        }
        throw error;
    }
);

export default AxiosClient;
//trebace nam za proveru tokena
