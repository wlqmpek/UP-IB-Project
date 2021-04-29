import React, {useEffect, useState} from 'react'
import validator from "validator";
import {PacijentService} from "../../services/PacijentService";
import { useParams } from "react-router";

const IzmenaPacijenta = () => {

    // const[patientToUpdate, setPatientToUpdate] = useState({})
    const[patient, setPatient] = useState({
        // id: patientToUpdate.id,
        // ime: patientToUpdate.ime,
        // prezime: patientToUpdate.prezime,
        // email: patientToUpdate.email,
        // lozinka: patientToUpdate.lozinka,
        // jbzo: patientToUpdate.jbzo
    })

    const {id} = useParams();

    useEffect(() => {
        fetchPatient(id)
    },[id])

    async function fetchPatient(id){
        try {
            const response = await PacijentService.getPacijent(id)
            setPatient(response.data)
        } catch (error){
            console.error(error)
        }
    }

    async function upadatePatient(){
        try {
            await PacijentService.editPacijent(patient.id, patient)
        }catch (error){
            console.error(error)
        }
    }

    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;
        setPatient({ ...patient, [name]: val });
    }

    const onSubmit = (e) => {
        e.preventDefault();

        if (!patient.ime || !patient.prezime || !patient.email || !patient.lozinka || !patient.jbzo) {
            alert('Sva polja su obavezna.')
            return
        }

        if (isNaN(patient.jbzo)) {
            alert('Jbzo mora sadrzat samo cifre')
            return
        }

        if (!validator.isEmail(patient.email)) {
            alert('Email nije validan')
            return
        }
        upadatePatient()

    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Izmena pacijenta</h3>
                    <form>
                        <div className='form-group'>
                            <label>Ime: </label>
                            <input type='text' value={patient.ime}  onChange={handleFormInputChange("ime")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Prezime: </label>
                            <input type='text' value={patient.prezime} onChange={handleFormInputChange("prezime")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Email: </label>
                            <input type='email' value={patient.email} onChange={handleFormInputChange("email")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Lozinka: </label>
                            <input type='password' disabled value={patient.lozinka} onChange={handleFormInputChange("lozinka")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>JBZO: </label>
                            <input type='text' value={patient.jbzo} onChange={handleFormInputChange("jbzo")}
                                   className='form-control'></input>
                        </div>
                        <button type='submit' className='btn btn-primary' onClick={onSubmit}>Izmeni</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default IzmenaPacijenta
