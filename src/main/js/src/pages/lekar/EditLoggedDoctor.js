import Select from "react-dropdown-select";
import React, {useEffect, useState} from "react";
import validator from "validator";
import {LekarService} from "../../services/LekarService";
import {AuthenticationService} from "../../services/AuthenticationService";
import {useHistory} from "react-router";

const EditLoggedDoctor = () => {

    const [doctor, setDoctor] = useState({})
    const email = AuthenticationService.getEmail()
    const history = useHistory()

    useEffect(()=>{
        fetchDoctor(email)
    }, [email])

    async function updateDoctor(){
        try {
            await LekarService.editLekar(doctor.idKorisnika, doctor)
        } catch (error){
            console.error(error)
        }
    }

    async function fetchDoctor(email){
        try {
            const response = await LekarService.getLekarByEmail(email)
            setDoctor(response.data)
        } catch (error){
            console.error(error)
        }
    }

    const handleFormInputChange = (name) => (event) =>{
        const val = event.target.value
        setDoctor({...doctor, [name]: val })
    }

    const onSend = (e) => {
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
        history.push("/lekar-pocetna")
    }

    return(
        <div className="container" style={{marginTop: "100px"}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Izmena podataka</h3>
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
                        <button type='submit' className='btn btn-primary' onClick={onSend}>Izmeni</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default EditLoggedDoctor