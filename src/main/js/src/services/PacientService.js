import AxiosClient from "./clients/AxiosClient"

export  const  PacientService = {
    getPacients,
    getPacient,
    createPacient,
    editPacient,
    deletePacient
}

async function getPacients() {
    return await AxiosClient.get("pacijenti")
}

async function getPacient(id) {
    return await AxiosClient.get(`pacijenti/${id}`)
}

async function createPacient(pacient) {
    return await AxiosClient.post("pacijenti", pacient)
}

async function editPacient(id, pacient) {
    return await AxiosClient.put(`pacijenti/${id}`, pacient)
}

async function deletePacient(id) {
    return await AxiosClient.delete(`pacijenti/${id}`)
}