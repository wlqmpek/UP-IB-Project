import AxiosClient from "./clients/AxiosClient";

export const MedicinkaSestraService = {

    getMSestre,
    getMSestra,
    createMSestra,
    editMSestra,
    deleteMSestra
}

async function getMSestre() {
    return await AxiosClient.get("MedicinskeSestre")
}

async function getMSestra(id) {
    return await AxiosClient.get(`MedicinskeSestre/${id}`)
}

async function createMSestra(msestra) {
    return await AxiosClient.post("MedicinskeSestre", msestra)
}

async function editMSestra(id, msestra) {
    return await AxiosClient.put(`MedicinskeSestre/${id}`, msestra)
}

async function deleteMSestra(id) {
    return await AxiosClient.delete(`MedicinskeSestre/${id}`)
}