import AxiousClient from "./clients/AxiousClient"

export const ReceiptService = {
    getReceipts,
    getReceipt,
    createReceipt,
    editReceipt,
    deleteReceipt
}

async function getReceipts() {
    return await AxiousClient.get("Recepti")
}

async function getReceipt(id) {
    return await AxiousClient.get(`Recepti/${id}`)
}

async function createReceipt(recept) {
    return await AxiousClient.post("Recepti", recept)
}

async function editReceipt(id, recept) {
    return await AxiousClient.put(`Recepti/${id}`, recept)
}

async function deleteReceipt(id) {
    return await AxiousClient.delete(`Recepti/${id}`)
}

export default ReceiptService;