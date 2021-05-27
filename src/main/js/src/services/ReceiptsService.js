import AxiosClient from "./clients/AxiosClient"

export const ReceiptsService = {
    getReceipts,
    getReceipt,
    createReceipt,
    editReceipt,
    deleteReceipt
}

async function getReceipts() {
    return await AxiosClient.get("Recepti")
}

async function getReceipt(id) {
    return await AxiosClient.get(`Recepti/${id}`)
}

async function createReceipt(recept) {
    return await AxiosClient.post("Recepti", recept)
}

async function editReceipt(id, recept) {
    return await AxiosClient.put(`Recepti/${id}`, recept)
}

async function deleteReceipt(id) {
    return await AxiosClient.delete(`Recepti/${id}`)
}

export default ReceiptsService;