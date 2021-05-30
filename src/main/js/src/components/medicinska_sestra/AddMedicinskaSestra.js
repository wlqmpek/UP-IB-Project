import React, {useEffect, useState} from "react";
import ClinicsService from "../../services/ClinicsService";
import {MedicinkaSestraService} from "../../services/MedicinkaSestraService";
import validator from "validator";
import Select from "react-dropdown-select";


const AddMedicinskaSestra = () =>{

    const [selectedOption, setSelectedOption] = useState(null)

    const [msestra, setMSestra] = useState({
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

    async function addMSestra(){
        try {
            await MedicinkaSestraService.createMSestra(msestra)
        }catch (error){
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

        if(!msestra.imeKorisnika || !msestra.prezimeKorisnika || !msestra.emailKorisnika || !msestra.lozinkaKorisnika){
            alert("Sva polja su obavezna")
            return
        }

        if(!validator.isEmail(msestra.emailKorisnika)){
            alert("Email nije ispravan")
            return
        }
        addMSestra()
        setMSestra({
            imeKorisnika: "",
            prezimeKorisnika: "",
            emailKorisnika: "",
            lozinkaKorisnika: "",
            idKlinike: ""
        })
    }

    const handleFormInputChange = (name) => (event) =>{
        const val = event.target.value
        setMSestra({...msestra, [name]: val })
    }

    const handleChange = selectedOption => {
        setSelectedOption({ selectedOption });
        setMSestra({...msestra, ["idKlinike"]: selectedOption[0].value})
    };

    return(
        <div className="container" style={{marginTop: "100px"}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Dodaj medicinsku sestru</h3>
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
                            <input type='password' value={msestra.lozinkaKorisnika} onChange={handleFormInputChange("lozinkaKorisnika")}
                                   className='form-control'/>
                        </div>
                        <div className="form-group">
                            <label>Klinika: </label>
                            <Select options={clinics} valueField={selectedOption} onChange={handleChange}/>
                        </div>
                        <button type='submit' className='btn btn-primary' onClick={onSubmit}>Dodaj</button>
                    </form>
                </div>
            </div>
        </div>
    )
}
export default AddMedicinskaSestra