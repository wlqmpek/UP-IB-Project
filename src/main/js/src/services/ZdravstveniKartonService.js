import AxiosClient from "./clients/AxiosClient"

export const ZdravstveniKartonService = {
    getZdravstveniKartoni,
    getZdravstveniKarton,
    createZdravstveniKarton,
    editZdravstveniKarton,
    deleteZdravstveniKarton
}

async function getZdravstveniKartoni() {
    return await AxiosClient.get("ZdravstveniKartoni")
}

async function getZdravstveniKarton(id) {
    return await AxiosClient.get(`ZdravstveniKartoni/${id}`)
}

async function createZdravstveniKarton(zdravstveniKarton) {
    return await AxiosClient.post("ZdravstveniKartoni", zdravstveniKarton)
}

async function editZdravstveniKarton(id, zdravstveniKarton) {
    return await AxiosClient.put(`ZdravstveniKartoni/${id}`, zdravstveniKarton)
}

async function deleteZdravstveniKarton(id) {
    return await AxiosClient.delete(`ZdravstveniKartoni/${id}`)
}

export default ZdravstveniKartonService;