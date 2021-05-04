import AxiousClient from "./clients/AxiousClient"

export const ZdravstveniKartonService = {
    getZdravstveniKartoni,
    getZdravstveniKarton,
    createZdravstveniKarton,
    editZdravstveniKarton,
    deleteZdravstveniKarton
}

async function getZdravstveniKartoni() {
    return await AxiousClient.get("ZdravstveniKartoni")
}

async function getZdravstveniKarton(id) {
    return await AxiousClient.get(`ZdravstveniKartoni/${id}`)
}

async function createZdravstveniKarton(zdravstveniKarton) {
    return await AxiousClient.post("ZdravstveniKartoni", zdravstveniKarton)
}

async function editZdravstveniKarton(id, zdravstveniKarton) {
    return await AxiousClient.put(`ZdravstveniKartoni/${id}`, zdravstveniKarton)
}

async function deleteZdravstveniKarton(id) {
    return await AxiousClient.delete(`ZdravstveniKartoni/${id}`)
}

export default ZdravstveniKartonService;