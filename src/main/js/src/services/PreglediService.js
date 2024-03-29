import AxiosClient from "./clients/AxiosClient"

export const PreglediService = {
    getPregledi,
    getPregled,
    createPregled,
    editPregled,
    deletePregled,
    createPregledLekar,
    createPregledCAdministrator,
    getPreglediForKlinika
}

async function getPregledi() {
    return await AxiosClient.get("Pregledi")
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

export default PreglediService;