import AxiosClient from "./clients/AxiosClient"

export const AdminService = {
    getAdmini,
    getAdmin,
    createAdmin,
    editAdmin,
    deleteAdmin
}

async function getAdmini() {
    return await AxiosClient.get("Admini")
}

async function getAdmin(id) {
    return await AxiosClient.get(`Admini/${id}`)
}

async function createAdmin(admin) {
    return await AxiosClient.post("Admini", admin)
}

async function editAdmin(id, admin) {
    return await AxiosClient.put(`Admini/${id}`, admin)
}

async function deleteAdmin(id) {
    return await AxiosClient.delete(`Admini/${id}`)
}

export default AdminService;