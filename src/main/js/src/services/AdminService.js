import AxiosClient from "./clients/AxiosClient"

export const AdminService = {
    getAdmini,
    getAdmin,
    createAdmin,
    editAdmin,
    deleteAdmin,
    getLoggedAdmin
}

async function getAdmini() {
    return await AxiosClient.get("Administratori")
}

async function getAdmin(id) {
    return await AxiosClient.get(`Administratori/${id}`)
}

async function createAdmin(admin) {
    return await AxiosClient.post("Administratori", admin)
}

async function editAdmin(id, admin) {
    return await AxiosClient.put(`Administratori/${id}`, admin)
}

async function deleteAdmin(id) {
    return await AxiosClient.delete(`Admini/${id}`)
}

async function getLoggedAdmin() {
    return await AxiosClient.get("Administratori/prijavljeni")
}
export default AdminService;