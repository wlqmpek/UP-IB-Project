import AxiosClient from "./clients/AxiosClient";

export const MedicinskaSestraService = {

    getMSestre,
    getMSestra,
    createMSestra,
    editMSestra,
    deleteMSestra,
    getMSestreForList
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

async function getMSestreForList() {
    return await AxiosClient.get("MedicinskeSestre/lista")
}
