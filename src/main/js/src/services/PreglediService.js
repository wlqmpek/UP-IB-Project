import AxiousClient from "./clients/AxiousClient"

export const PreglediService = {
    getPregledi,
    getPregled,
    createPregled,
    editPregled,
    deletePregled
}

async function getPregledi() {
    return await AxiousClient.get("Pregledi")
}

async function getPregled(id) {
    return await AxiousClient.get(`Pregledi/${id}`)
}

async function createPregled(pregled) {
    return await AxiousClient.post("Pregledi", pregled)
}

async function editPregled(id, pregled) {
    return await AxiousClient.put(`Pregledi/${id}`, pregled)
}

async function deletePregled(id) {
    return await AxiousClient.delete(`Pregledi/${id}`)
}

export default PreglediService;