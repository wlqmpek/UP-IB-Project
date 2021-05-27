import AxiosClient from "./clients/AxiosClient"

export const MedSestraService = {
    getMedSestre,
    getMedSestra,
    createMedSestra,
    editMedSestra,
    deleteMedSestra
}

async function getMedSestre() {
    return await AxiosClient.get("MedSestre")
}

async function getMedSestra(id) {
    return await AxiosClient.get(`MedSestre/${id}`)
}

async function createMedSestra(medSestra) {
    return await AxiosClient.post("MedSestre", medSestra)
}

async function editMedSestra(id, medSestra) {
    return await AxiosClient.put(`MedSestre/${id}`, medSestra)
}

async function deleteMedSestra(id) {
    return await AxiosClient.delete(`MedSestre/${id}`)
}

export default MedSestraService;