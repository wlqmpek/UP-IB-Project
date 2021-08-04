import 'bootstrap/dist/css/bootstrap.min.css'
import AxiosClient from "./clients/AxiosClient"

export const ClinicsService = {
    getClinics,
    getClinicById,
    createClinic,
    updateClinic,
    deleteClinic,
    getClinicsList,
    pretraziKlinike,
    ocenaKlinike
}

async function getClinics() {
    return await AxiosClient.get("Klinike")
}

async function getClinicById(clinicId) {
    return await AxiosClient.get(`Klinike/${clinicId}`)
}

async function createClinic(clinic) {
    return await AxiosClient.post("Klinike", clinic)
}

async function updateClinic(clinic, clinicId) {
    return await AxiosClient.put(`Klinike/${clinicId}`, clinic)
}

async function deleteClinic(clinicId) {
    return await AxiosClient.delete(`Klinike/${clinicId}`)
}

async function getClinicsList(){
    return await AxiosClient.get("Klinike/lista");
}

async function pretraziKlinike(parametriPretrage) {
    return await AxiosClient.post(`Klinike/pretraga/`, parametriPretrage);
}

async function ocenaKlinike(idKlinike) {
    return await AxiosClient.get(`Klinike/ocene/${idKlinike}`)
}

export default ClinicsService;