import AxiosClient from "./clients/AxiosClient";
import { TokenService } from "./TokenService";

export const AuthenticationService = {
    login,
    logout,
    getRole,
};

async function login(userCredentials) {
    try {
        const response = await AxiosClient.post(
            "/korisnici/prijava",
            userCredentials
        );
        console.log(response);
        console.log(response.data);
        console.log(response.data.accessToken);
        const decoded_token = TokenService.decodeToken(response.data.accessToken);
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
    const decoded_token = token ? TokenService.decodeToken(token) : null;
    if (decoded_token) {
        return decoded_token.role.authority;
    } else {
        return null;
    }
}