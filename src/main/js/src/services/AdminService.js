import AxiousClient from "./clients/AxiousClient"

export const AdminService = {
    getAdmini,
    getAdmin,
    createAdmin,
    editAdmin,
    deleteAdmin
}

async function getAdmini() {
    return await AxiousClient.get("Admini")
}

async function getAdmin(id) {
    return await AxiousClient.get(`Admini/${id}`)
}

async function createAdmin(admin) {
    return await AxiousClient.post("Admini", admin)
}

async function editAdmin(id, admin) {
    return await AxiousClient.put(`Admini/${id}`, admin)
}

async function deleteAdmin(id) {
    return await AxiousClient.delete(`Admini/${id}`)
}

export default AdminService;