import AxiosClient from "../clients/AxiosClient";

export const PatientService = {
    // getPatients,
    // getPatient,
    // isPatientValid,
    editPatient,
    // blockPatient
    getPatient
}

async function getPatient(id) {
    return await AxiosClient.get(`pacijenti/${id}`);
}

async function editPatient(id, patient) {
    return await AxiosClient.put(`pacijenti/${id}`, patient);
}