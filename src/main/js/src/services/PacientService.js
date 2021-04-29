import AxiousClient from "./clients/AxiousClient"

export  const  PacientService = {
    getPacients,
    getPacient,
    createPacient,
    editPacient,
    deletePacient
}

async function getPacients() {
    return await AxiousClient.get("Pacijenti")
}

async function getPacient(id) {
    return await AxiousClient.get(`Pacijenti/${id}`)
}

async function createPacient(pacient) {
    return await AxiousClient.post("Pacijenti", pacient)
}

async function editPacient(id, pacient) {
    return await AxiousClient.put(`Pacijenti/${id}`, pacient)
}

async function deletePacient(id) {
    return await AxiousClient.delete(`Pacijenti/${id}`)
}