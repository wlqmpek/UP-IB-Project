import AxiosClient from "./clients/AxiosClient";
import { TokenService } from "./TokenService";

export const AuthenticationService = {
    login,
    logout,
    getRole,
    getEmail
};

async function login(userCredentials) {
    try {
        const response = await AxiosClient.post(
            "/korisnici/prijava",
            userCredentials
        );
        const decoded_token = TokenService.decodeToken(response.data);
        console.log("Decoded " + decoded_token);
        if(decoded_token) {
            TokenService.setToken(response.data);
            window.location.assign("/");
        } else {
            console.log("NOPEEE");
            console.log("Invalid token");
        }
    } catch (error) {
        console.log("Status " + error.response.status);
        throw error;
    }
}

function logout() {
    TokenService.removeToken();
    window.location.assign("/");
}

function getRole() {
    const token = TokenService.getToken();
    console.log(console.log("object: %O", token));
    const decoded_token = token ? TokenService.decodeToken(token) : null;
    console.log("Decoded token roles " + decoded_token.roles);
    if (decoded_token) {
        return decoded_token.roles;
    } else {
        return null;
    }
}

function getEmail() {
    const token = TokenService.getToken();
    const decoded_token = token ? TokenService.decodeToken(token) : null;
    if (decoded_token) {
        return decoded_token.sub;
    } else {
        return null;
    }
}