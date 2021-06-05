import AxiosClient from "./clients/AxiosClient";
import { TokenService } from "./TokenService";
import { MedicinskaSestraService } from "./MedicinskaSestraService";
import LekarService from "./LekarService";


export const AuthenticationService = {
    login,
    refresh,
    logout,
    getRole,
    getEmail
};

async function login(userCredentials) {
    TokenService.removeAccessToken()
    try {
        const response = await AxiosClient.post(
            "/korisnici/prijava",
            userCredentials
        );
        const decoded_token = TokenService.decodeAccessToken(response.data.token);
        console.log("Decoded " + decoded_token);
        if (decoded_token) {
            TokenService.setId(response.data.id);
            // TokenService.setId(response.data.id);
            // TokenService.setId(response.data.id);
            // TokenService.setId(response.data.id);
            // TokenService.setId(response.data.id);
            TokenService.setAccessToken(response.data.token);
            TokenService.setRefreshToken(response.data.refreshToken);

            // ukoliko je ulogovana medicinska sestra preusmjerava se na njenu stranicu
            if (this.getRole().includes("ROLE_MEDICINSKA_SESTRA")) {
                const idMedSestre = response.data.id;
                MedicinskaSestraService.getMSestra(idMedSestre).then(res => {
                    window.location.assign(`/medicinske-sestre/${idMedSestre}/klinika/${res.data.idKlinike}`);
                });
            }
            else if (this.getRole().includes("ROLE_LEKAR")) {
                const idLekara = response.data.id;
                LekarService.getLekar(idLekara).then(res => {
                    window.location.assign(`/${idLekara}/radniKalendar/${res.data.idKlinike}`);
                });
            }
            else {
                window.location.assign("/");
            }
        } else {
            console.log("NOPEEE");
            console.log("Invalid token");
        }
    } catch (error) {
        throw error;
    }
}
//DODATO NOVO???
async function refresh() {
    TokenService.removeAccessToken();
    try {
        const response = await AxiosClient.post(
            "korisnici/refreshtoken", {"refreshToken":TokenService.getRefreshToken()}
        );
        console.log("Response data " + response.data.accessToken)
        const decoded_token = TokenService.decodeAccessToken(response.data.accessToken);
        console.log("Decoded u refreshu " + decoded_token);
        if(decoded_token) {
            TokenService.setAccessToken(response.data.accessToken);
            console.log("Refreshovan accessToken " + TokenService.getAccessToken())
        } else {
            console.log("NOPE");
            console.log("Invalid token");
        }
    } catch (error) {
        console.log("Refreshovanje tokena nije uspelo i kod je " + error);
        throw error;
    }
}

function logout() {
    TokenService.removeAccessToken();
    window.location.assign("/");
}

function getRole() {
    console.log(TokenService.getAccessToken());
    const token = TokenService.getAccessToken();
    console.log(console.log("object: %O", token));
    const decoded_token = token ? TokenService.decodeAccessToken(token) : null;
    console.log("Decoded token roles " + decoded_token.roles);
    if (decoded_token) {
        return decoded_token.roles;
    } else {
        return null;
    }
}



function getEmail() {
    const token = TokenService.getAccessToken();
    const decoded_token = token ? TokenService.decodeAccessToken(token) : null;
    if (decoded_token) {
        return decoded_token.sub;
    } else {
        return null;
    }
}

