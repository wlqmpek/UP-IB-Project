import AxiosClient from "./clients/AxiosClient"

export const PreglediService = {
    getPregledi,
    getPreglediPacijenta,
    getPregled,
    createPregled,
    editPregled,
    deletePregled,
    createPregledLekar,
    createPregledCAdministrator,
    getPreglediForKlinika,
    getPreglediKlinike,
    pretragaPregleda,
    posaljiMejl,
    potvrdaTermina
}

async function getPregledi() {
    return await AxiosClient.get("Pregledi")
}

//Koristi se za dobavljanje svih pregleda pacijenta, od strane pacijenta. - WLQ
async function getPreglediPacijenta() {
    return await AxiosClient.get("Pregledi/pacijenti")
}

async function getPregled(id) {
    return await AxiosClient.get(`Pregledi/${id}`)
}

async function createPregled(pregled) {
    return await AxiosClient.post("Pregledi", pregled)
}

async function editPregled(id, pregled) {
    return await AxiosClient.put(`Pregledi/${id}`, pregled)
}

async function deletePregled(id) {
    return await AxiosClient.delete(`Pregledi/${id}`)
}

async function createPregledLekar(pregled) {
    return await AxiosClient.post("Pregledi/pregled/lekar", pregled)
}

async function createPregledCAdministrator(pregled) {
    return await AxiosClient.post("Pregledi/pregled/administrator", pregled)
}

async function getPreglediForKlinika(){
    return await AxiosClient.get("Pregledi/klinika")
}

async function getPreglediKlinike(id) {
    return await AxiosClient.get(`Pregledi/klinika/${id}`)
}

async function pretragaPregleda(parametri) {
    return await AxiosClient.get(`Pregledi/pretraga?idKlinike=${parametri.idKlinike}&odDatuma=${parametri.odDatuma}`)
}

async function posaljiMejl(idKorisnika, idPregleda) {
    const parametri = {idKorisnika:idKorisnika, idPregleda:idPregleda};
    return await AxiosClient.post(`Pregledi/zakazivanje`, parametri);
}

async function potvrdaTermina(idKorisnika, idPregleda) {
    const parametri = {idKorisnika:idKorisnika, idPregleda:idPregleda};
    return await AxiosClient.post(`Pregledi/potvrda-termina`, parametri);
}

export default PreglediService;