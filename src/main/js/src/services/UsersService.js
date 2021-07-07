import AxiosClient from "./clients/AxiosClient"

export const UsersService = {
    changePassword,
    getInfo,
    updateInfo
}

async function changePassword(password) {
    return await AxiosClient.put("korisnici/promena-lozinke", password)
}

async function getInfo(){
    return await AxiosClient.get("korisnici/korisnik-info")
}

async function updateInfo(user) {
    return await AxiosClient.put("korisnici/izmena-podataka", user)
}