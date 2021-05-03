import AxiousClient from "./clients/AxiousClient"

export const LekarService = {
    getLekari,
    getLekar,
    createLekar,
    editLekar,
    deleteLekar
}

async function getLekari() {
    return await AxiousClient.get("Lekari")
}

async function getLekar(id) {
    return await AxiousClient.get(`Lekari/${id}`)
}

async function createLekar(lekar) {
    return await AxiousClient.post("Lekari", lekar)
}

async function editLekar(id, lekar) {
    return await AxiousClient.put(`Lekari/${id}`, lekar)
}

async function deleteLekar(id) {
    return await AxiousClient.delete(`Lekari/${id}`)
}

export default LekarService;