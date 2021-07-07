import React, {useEffect, useState} from "react";
import {useHistory} from "react-router";
import {LekarService} from "../../services/LekarService";
import validator from "validator";
import AdminService from "../../services/AdminService";

const AddLekar = () => {

    const [warning, setWarning] = useState("")
    const [admin, setAdmin] = useState({})
    const [doctor, setDoctor] = useState({
        imeKorisnika: "",
        prezimeKorisnika: "",
        emailKorisnika: "",
        lozinkaKorisnika: "",
        idKlinike: ""
    })
    const history = useHistory()

    async function addDoctor(){
        try {
            await LekarService.createLekar(doctor)
        } catch (error){
            console.error(error)
        }
    }

    async function getAdmin() {
        try {
            const response = await AdminService.getLoggedAdmin()
            setAdmin(response.data)
        } catch (e){
            console.error(e)
        }
    }

    useEffect(()=>{
        getAdmin()
    },[])

    useEffect(()=>{

        setDoctor({...doctor, idKlinike: admin.idKlinike})
    }, [admin])

    const onSubmit = (e) => {
        e.preventDefault()

        if(!doctor.imeKorisnika || !doctor.prezimeKorisnika || !doctor.emailKorisnika || !doctor.lozinkaKorisnika){
            setWarning("Sva polja su obavezna")
            return
        }

        if(!validator.isEmail(doctor.emailKorisnika)){
            setWarning("Email nije ispravan")
            return
        }

        addDoctor()
        setDoctor({
            imeKorisnika: "",
            prezimeKorisnika: "",
            emailKorisnika: "",
            lozinkaKorisnika: "",
            idKlinike: ""
        })
        history.push("/lekari/klinika")
    }

    const handleFormInputChange = (name) => (event) =>{
        const val = event.target.value
        setDoctor({...doctor, [name]: val })
    }

    return(
        <div className="container" style={{marginTop: "100px"}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Dodaj doktora</h3>
                    <form>
                        <div className='form-group'>
                            <label>Ime: </label>
                            <input type='text' value={doctor.imeKorisnika}  onChange={handleFormInputChange("imeKorisnika")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Prezime: </label>
                            <input type='text' value={doctor.prezimeKorisnika} onChange={handleFormInputChange("prezimeKorisnika")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Email: </label>
                            <input type='email' value={doctor.emailKorisnika} onChange={handleFormInputChange("emailKorisnika")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Lozinka: </label>
                            <input type='password' value={doctor.lozinkaKorisnika} onChange={handleFormInputChange("lozinkaKorisnika")}
                                   className='form-control'/>
                        </div>
                        <p className="text-danger">{warning}</p>
                        <button type='submit' className='btn btn-primary' onClick={onSubmit}>Dodaj</button>
                    </form>
                </div>
            </div>
        </div>
    )

}

export default AddLekar