import jwtDecode from "jwt-decode";

// Fajl sluzi za rad sa tokenom iz lokalnog skladista. - WLQ
export const TokenService = {
    // Sve funkcije koje ovde nisu navedene smatraju se privatnim, dok su navedene funkcije javne. - WLQ
    getAccessToken, setAccessToken, removeAccessToken, decodeAccessToken, didAccessTokenExpire, getRefreshToken, setRefreshToken, removeRefreshToken
};

function getAccessToken() {
    return localStorage.getItem("accessToken");
}

function getRefreshToken() {
    return localStorage.getItem("refreshToken")
}

function setAccessToken(value) {
    localStorage.setItem("accessToken", value);
}

function setRefreshToken(value) {
    localStorage.setItem("refreshToken", value);
}

function removeAccessToken() {
    localStorage.removeItem("accessToken");
}

function removeRefreshToken() {
    localStorage.removeItem("refreshToken");
}

function decodeAccessToken(token) {
    console.log("Token za dekodiranje " +token);
    try {
        return jwtDecode(token);
    } catch (error) {
        console.log(error);
        console.log("Nije uspesno dekodovan");
        return null;
    }
}

function didAccessTokenExpire() {
    const token = getAccessToken();
    // JS automatski ovo konvertuje u true ili false u zavisnosti da li je token token ili je token null. - WLQ
    const decodedToken = token ? decodeAccessToken(token) : null;

    /* Vraca true ako decodedToken nije istekao, a false ako decodedToken ne postoji ili ako je datum isteka
    pre sadasnjeg trenutka. - WLQ */
    return decodedToken ? decodedToken.exp_date < Date.now() : null;
}