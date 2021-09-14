import AxiosClient from "./clients/AxiosClient"

export const PacijentService = {
    getPacijents,
    getPacijent,
    createPacijent,
    editPacijent,
    editPacijentDetails,
    deletePacijent
}

async function getPacijents() {
    return await AxiosClient.get("Pacijenti")
}

async function getPacijent(id) {
    return await AxiosClient.get(`Pacijenti/${id}`)
}

async function createPacijent(pacijent) {
    return await AxiosClient.post("Pacijenti", pacijent)
}

async function editPacijent(id, pacijent) {
    return await AxiosClient.put(`Pacijenti/${id}`, pacijent)
}

//Gadja endpoint koji sluzi da pacijent menja sopstvene detalje.
async function editPacijentDetails(patient) {
    return await AxiosClient.put(`Pacijenti/izmeni`, patient);
}

async function deletePacijent(id) {
    return await AxiosClient.delete(`Pacijenti/${id}`)
}

export default PacijentService;
