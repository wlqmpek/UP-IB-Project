import axious from "axios";


const AxiousClient = axious.create({
    baseURL: 'http://localhost:8080/KlinickiCentar/'
})

export default AxiousClient
//trebace nam za proveru tokena
