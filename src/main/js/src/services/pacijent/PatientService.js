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
    return await AxiosClient.get(`Pacijenti/${id}`);
}

async function editPatient(id, patient) {
    return await AxiosClient.put(`Pacijenti/${id}`, patient);
}