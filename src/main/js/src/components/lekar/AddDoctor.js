import React, {useEffect, useState} from "react";
import {LekarService} from "../../services/LekarService";
import validator from "validator";
import Select from "react-dropdown-select";
import ClinicsService from "../../services/ClinicsService";


const AddDoctor = () => {

    const [doctor, setDoctor] = useState({
        imeKorisnika: "",
        prezimeKorisnika: "",
        emailKorisnika: "",
        lozinkaKorisnika: "",
        idKlinike: ""
    })
    const [clinics, setClinics] = useState([])

    useEffect(()=>{
        fetchClinics()
    },[])
    async function addDoctor(){
        try {
            await LekarService.createLekar(doctor)
        } catch (error){
            console.error(error)
        }
    }

    async function fetchClinics(){
        try {
            const response = await ClinicsService.getClinicsList()
            setClinics(response.data)
        }catch (error){
            console.error(error)
        }
    }
    const onSubmit = (e) => {
        e.preventDefault()

        if(!doctor.imeKorisnika || !doctor.prezimeKorisnika || !doctor.emailKorisnika || !doctor.lozinkaKorisnika){
            alert("Sva polja su obavezna")
            return
        }

        if(!validator.isEmail(doctor.emailKorisnika)){
            alert("Email nije ispravan")
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
    }

    const handleFormInputChange = (name) => (event) =>{
        const val = event.target.value
        setDoctor({...doctor, [name]: val })
    }

    const changeIdKlinike = (clinic) => {
        setDoctor({...doctor, ["idKlinike"]: clinic.id})
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
                        <div className="form-group">
                            <label>Klinika: </label>
                            <Select  options={clinics} onChange={(clinic)=>changeIdKlinike(clinic)}/>
                        </div>
                        <button type='submit' className='btn btn-primary' onClick={onSubmit}>Dodaj</button>
                    </form>
                </div>
            </div>
        </div>
    )

}
export default AddDoctor