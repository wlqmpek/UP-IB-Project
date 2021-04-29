import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios';

const CLINICS_API_BASE_URL = "http://localhost:8080/KlinickiCentar/Klinike";

class ClinicsService {
    getClinics() {
        return axios.get(CLINICS_API_BASE_URL);
    }

    getClinicById(clinicId) {
        return axios.get(CLINICS_API_BASE_URL + "/" + clinicId);
    }

    createClinic(clinic) {
        return axios.post(CLINICS_API_BASE_URL, clinic);
    }

    updateClinic(clinic, clinicId) {
        return axios.put(CLINICS_API_BASE_URL + "/" + clinicId, clinic);
    }

    deleteClinic(clinicId) {
        return axios.delete(CLINICS_API_BASE_URL + "/" + clinicId);
    }
}

export default new ClinicsService();
