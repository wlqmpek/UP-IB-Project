import AxiosClient from "./clients/AxiosClient"

export const LekarService = {
    getLekari,
    getLekar,
    getLekarByEmail,
    createLekar,
    editLekar,
    deleteLekar,
    changePassword,
    getLekariList,
    getLekariByKlinika,
    getLekariUTerminu
}

async function getLekari() {
    return await AxiosClient.get("Lekari")
}

async function getLekar(id) {
    return await AxiosClient.get(`Lekari/${id}`)
}

async function getLekarByEmail(email){
    return await AxiosClient.get(`Lekari/nadji/${email}`)
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

async function changePassword(password) {
    return await AxiosClient.post("Lekari/promeni/lozinku", password)
}

async function getLekariList() {
    return await AxiosClient.get("Lekari/lista")
}

async function getLekariByKlinika(){
    return await AxiosClient.get("Lekari/klinika")
}

async function getLekariUTerminu(termin) {
    return await AxiosClient.post("Lekari/termin", termin);
}


export default LekarService;