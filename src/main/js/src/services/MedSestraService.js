import AxiousClient from "./clients/AxiousClient"

export const MedSestraService = {
    getMedSestre,
    getMedSestra,
    createMedSestra,
    editMedSestra,
    deleteMedSestra
}

async function getMedSestre() {
    return await AxiousClient.get("MedSestre")
}

async function getMedSestra(id) {
    return await AxiousClient.get(`MedSestre/${id}`)
}

async function createMedSestra(medSestra) {
    return await AxiousClient.post("MedSestre", medSestra)
}

async function editMedSestra(id, medSestra) {
    return await AxiousClient.put(`MedSestre/${id}`, medSestra)
}

async function deleteMedSestra(id) {
    return await AxiousClient.delete(`MedSestre/${id}`)
}

export default MedSestraService;