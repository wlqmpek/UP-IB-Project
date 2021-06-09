import React, {useEffect, useState} from "react";
import {useParams} from "react-router";
import {LekarService} from "../../services/LekarService";
import ClinicsService from "../../services/ClinicsService";
import validator from "validator";
import Select from "react-dropdown-select";


const EditDoctor = () =>{

    const [selectedOption, setSelectedOption] = useState(null)
    const [doctor, setDoctor] = useState({})
    const [clinics, setClinics] = useState([])

    const {id} = useParams()

    useEffect(()=>{
        fetchDoctor(id)
        fetchClinics()
    }, [id])
    async function fetchDoctor(id){
        try {
            const response = await LekarService.getLekar(id)
            setDoctor(response.data)
        } catch (error){
            console.error(error)
        }
    }

    async function fetchClinics(){
        try {
            const response = await ClinicsService.getClinicsList()
            setClinics(response.data)
        } catch (error){
            console.error(error)
        }
    }

    async function updateDoctor(){
        try {
            await LekarService.editLekar(doctor.idKorisnika, doctor)
        } catch (error){
            console.error(error)
        }
    }

    const handleFormInputChange = (name) => (event) =>{
        const val = event.target.value
        setDoctor({...doctor, [name]: val })
    }

    const handleChange = selectedOption => {
        setSelectedOption({ selectedOption });
        setDoctor({...doctor, ["idKlinike"]: selectedOption[0].value})
    };


    const onSubmit = (e) => {
        e.preventDefault()

        if(!doctor.imeKorisnika || !doctor.prezimeKorisnika || !doctor.emailKorisnika){
            alert("Sva polja su obavezna")
            return
        }

        if(!validator.isEmail(doctor.emailKorisnika)){
            alert("Email nije ispravan")
            return
        }
        updateDoctor()
    }

    return(
        <div className="container" style={{marginTop: "100px"}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Izmena doktora</h3>
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
{/*                        <div className='form-group'>
                            <label>Lozinka: </label>
                            <input type='password' disabled value={doctor.lozinkaKorisnika} onChange={handleFormInputChange("lozinkaKorisnika")}
                                   className='form-control'/>
                        </div>*/}
                        <div className="form-group">
                            <label>Klinika: </label>
                            <Select  options={clinics} valueField={selectedOption} onChange={handleChange}/>
                        </div>
                        <button type='submit' className='btn btn-primary' onClick={onSubmit}>Izmeni</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default EditDoctor