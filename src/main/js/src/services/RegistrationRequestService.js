import AxiosClient from "./clients/AxiosClient";

export const RegistrationRequestService = {
    getRequests,
    editRequest,
    getLink
}

async function getRequests() {
    return await AxiosClient.get("Zahtevi")
}

async function editRequest(id, request) {
    return await AxiosClient.put(`Zahtevi/${id}`, request)
}

async function getLink(path){
    return await AxiosClient.get(`Zahtevi/Nadji/${path}`)
}