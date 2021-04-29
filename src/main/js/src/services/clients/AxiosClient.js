import axios from "axios";


const AxiosClient = axios.create({
    baseURL: 'http://localhost:8080/KlinickiCentar/'
})

export default AxiosClient
//trebace nam za proveru tokena
