import React, {useEffect, useState} from "react";
import {useParams} from "react-router";
import { MedicinskaSestraService } from "../../services/MedicinskaSestraService";
import ClinicsService from "../../services/ClinicsService";
import validator from "validator";
import Select from "react-dropdown-select";


const EditMedicinkaSestra = () =>{

    const [selectedOption, setSelectedOption] = useState(null)
    const [msestra, setMSestra] = useState({})
    const [clinics, setClinics] = useState([])
    
    const {id} = useParams()
    
    useEffect(()=>{
        fetchMSestra(id)
        fetchClinics()
    },[id])
    
    async function fetchMSestra(id){
        try {
            const response = await MedicinskaSestraService.getMSestra(id)
            setMSestra(response.data)
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
    
    async function updateMSestra(){
        try {
            await MedicinskaSestraService.editMSestra(msestra.idKorisnika, msestra)
        }catch (error){
            console.error(error)
        }
    }

    const handleFormInputChange = (name) => (event) =>{
        const val = event.target.value
        setMSestra({...msestra, [name]: val })
    }

    const handleChange = selectedOption => {
        setSelectedOption({ selectedOption });
        setMSestra({...msestra, ["idKlinike"]: selectedOption[0].value})
    };

    const onSubmit = (e) => {
        e.preventDefault()

        if(!msestra.imeKorisnika || !msestra.prezimeKorisnika || !msestra.emailKorisnika || !msestra.lozinkaKorisnika){
            alert("Sva polja su obavezna")
            return
        }

        if(!validator.isEmail(msestra.emailKorisnika)){
            alert("Email nije ispravan")
            return
        }
        updateMSestra()
    }

    return(
        <div className="container" style={{marginTop: "100px"}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Izmena medicinske sestre</h3>
                    <form>
                        <div className='form-group'>
                            <label>Ime: </label>
                            <input type='text' value={msestra.imeKorisnika}  onChange={handleFormInputChange("imeKorisnika")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Prezime: </label>
                            <input type='text' value={msestra.prezimeKorisnika} onChange={handleFormInputChange("prezimeKorisnika")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Email: </label>
                            <input type='email' value={msestra.emailKorisnika} onChange={handleFormInputChange("emailKorisnika")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Lozinka: </label>
                            <input type='password' disabled value={msestra.lozinkaKorisnika} onChange={handleFormInputChange("lozinkaKorisnika")}
                                   className='form-control'/>
                        </div>
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
export default EditMedicinkaSestra