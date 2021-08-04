export const ParametriPretrageService = {
    getParametri,
    setParametri
}

function setParametri(parametri) {
    localStorage.setItem("parametri", JSON.stringify(parametri));
}

function getParametri() {
    return JSON.parse(localStorage.getItem("parametri"));
}


