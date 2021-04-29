import AxiosClient from "./clients/AxiosClient";

export const MedicinkaSestraService = {}

async function getMSestre() {
    return await AxiosClient.get("MedicinkseSestre")
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