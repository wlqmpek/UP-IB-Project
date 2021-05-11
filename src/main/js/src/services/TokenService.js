import jwt_decode from "jwt-decode";

// Fajl sluzi za rad sa tokenom iz lokalnog skladista. - WLQ
export const TokenService = {
    // Sve funkcije koje ovde nisu navedene smatraju se privatnim, dok su navedene funkcije javne. - WLQ
    getToken, setToken, removeToken, decodeToken, didTokenExpire,
};

function getToken() {
    return localStorage.getItem("token");
}

function setToken(value) {
    localStorage.setItem("token", value);
}

function removeToken() {
    localStorage.removeItem("token");
}

function decodeToken(token) {
    console.log("Token " + token);
    try {
        return jwt_decode(token);
    } catch (error) {
        console.log(error);
        console.log("Nije uspesno dekodovan");
        return null;
    }
}

function didTokenExpire() {
    const token = getToken();
    // JS automatski ovo konvertuje u true ili false u zavisnosti da li je token token ili je token null. - WLQ
    const decodedToken = token ? decodeToken(token) : null;

    /* Vraca true ako decodedToken nije istekao, a false ako decodedToken ne postoji ili ako je datum isteka
    pre sadasnjeg trenutka. - WLQ */
    return decodedToken ? decodedToken.exp_date < Date.now() : null;
}