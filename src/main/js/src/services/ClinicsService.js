import 'bootstrap/dist/css/bootstrap.min.css'
import AxiousClient from "./clients/AxiousClient"

export const ClinicsService = {
    getClinics,
    getClinicById,
    createClinic,
    updateClinic,
    deleteClinic
}

async function getClinics() {
    return await AxiousClient.get("Klinike")
}

async function getClinicById(clinicId) {
    return await AxiousClient.get(`Klinike/${clinicId}`)
}

async function createClinic(clinic) {
    return await AxiousClient.post("Klinike", clinic)
}

async function updateClinic(clinic, clinicId) {
    return await AxiousClient.put(`Klinike/${clinicId}`, clinic)
}

async function deleteClinic(clinicId) {
    return await AxiousClient.delete(`Klinike/${clinicId}`)
}

export default ClinicsService;