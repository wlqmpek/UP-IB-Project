import React, {useEffect, useState} from "react";
import validator from "validator";
import {LekarService} from "../services/LekarService";
import {useHistory} from "react-router";
import {UsersService} from "../services/UsersService";

const EditLoggedUser = () => {

    const [warning, setWarning] = useState("")
    const [user, setUser] = useState({})
    const history = useHistory()

    useEffect(()=>{
        fetchUser()
    }, [])

    async function updateUser(){
        try {
            await UsersService.updateInfo(user)
        } catch (error){
            console.error(error)
        }
    }

    async function fetchUser(){
        try {
            const response = await UsersService.getInfo()
            setUser(response.data)
        } catch (error){
            console.error(error)
        }
    }

    const handleFormInputChange = (name) => (event) =>{
        const val = event.target.value
        setUser({...user, [name]: val })
    }

    const onSend = (e) => {
        e.preventDefault()

        if(!user.ime || !user.prezime || !user.email){
            setWarning("Sva polja su obavezna")
            return
        }

        if(!validator.isEmail(user.email)){
            setWarning("Email nije ispravan")
            return
        }
        updateUser()
        history.push("/")
    }

    return(
        <div className="container" style={{marginTop: "100px"}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Izmena podataka</h3>
                    <form>
                        <div className='form-group'>
                            <label>Ime: </label>
                            <input type='text' value={user.ime}  onChange={handleFormInputChange("ime")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Prezime: </label>
                            <input type='text' value={user.prezime} onChange={handleFormInputChange("prezime")}
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Email: </label>
                            <input type='email' value={user.email} onChange={handleFormInputChange("email")}
                                   className='form-control'/>
                        </div>
                        <p className="text-danger">{warning}</p>
                        <button type='submit' className='btn btn-primary' onClick={onSend}>Izmeni</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default EditLoggedUser