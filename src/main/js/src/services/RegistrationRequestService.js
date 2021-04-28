import AxiousClient from "./clients/AxiousClient";

export const RegistrationRequestService = {
    getRequests,
    editRequest
}

async function getRequests(){
    return await AxiousClient.get("Zahtevi")
}

async function editRequest(id, request){
    return await AxiousClient.put(`Zahtevi/${id}`, request)
}