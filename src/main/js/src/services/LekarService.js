import AxiosClient from "./clients/AxiosClient";

export const LekarService = {
    getLekars,
    getLekar,
    createLekar,
    editLekar,
    deleteLekar
}

async function getLekars() {
    return await AxiosClient.get("Lekari")
}

async function getLekar(id) {
    return await AxiosClient.get(`Lekari/${id}`)
}

async function createLekar(lekar) {
    return await AxiosClient.post("Lekari", lekar)
}

async function editLekar(id, lekar) {
    return await AxiosClient.put(`Lekari/${id}`, lekar)
}

async function deleteLekar(id) {
    return await AxiosClient.delete(`Lekari/${id}`)
}
