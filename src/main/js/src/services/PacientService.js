import AxiosClient from "./clients/AxiosClient"

export  const  PacientService = {
    getPacients,
    getPacient,
    createPacient,
    editPacient,
    deletePacient
}

async function getPacients() {
    return await AxiosClient.get("Pacijenti")
}

async function getPacient(id) {
    return await AxiosClient.get(`Pacijenti/${id}`)
}

async function createPacient(pacient) {
    return await AxiosClient.post("Pacijenti", pacient)
}

async function editPacient(id, pacient) {
    return await AxiosClient.put(`Pacijenti/${id}`, pacient)
}

async function deletePacient(id) {
    return await AxiosClient.delete(`Pacijenti/${id}`)
}