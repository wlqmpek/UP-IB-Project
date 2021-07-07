import React, {useState} from "react";
import {useHistory} from "react-router";
import {UsersService} from "../services/UsersService";

const PasswordChange = () =>{

    const [password, setPassword] = useState({
        password: "",
        currentPassword: ""
    })
    const [pass, setPass] = useState("")
    const [warning, setWarning] = useState("")
    const history = useHistory()

    async function changePassword(password) {
        try {
            await UsersService.changePassword(password)
        } catch (e) {
            console.error(e)
        }
    }

    const handleFormInputChange = (name) => (event) =>{
        const val = event.target.value
        setPassword({...password, [name]: val })
    }

    const onSubmit = (e) => {
        e.preventDefault()

        if(!pass || !password.currentPassword || !password.password){
            setWarning("Sva polja moraju biti popunjena")
            return
        }
        if(pass !== password.password){
            setWarning("Ponovljena lozinka se ne poklapa")
            return
        }

        changePassword(password).catch(()=>{setWarning("Ne uspesno promenjena lozinka"); return})
        history.push("/")
    }

    return(
        <div className="container-fluid" style={{marginTop: "100px"}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Promena lozinke</h3>
                    <form>
                        <div className="form-group">
                            <label>Trenutna lozinka: </label>
                            <input type="password" className="form-control" value={password.currentPassword}
                                   onChange={handleFormInputChange("currentPassword")}/>
                        </div>
                        <div className="form-group">
                            <label>Nova lozinka: </label>
                            <input type="password" className="form-control" value={password.password}
                                   onChange={handleFormInputChange("password")}/>
                        </div>
                        <div className="form-group">
                            <label>Ponovljena nova lozinka: </label>
                            <input type="password" className="form-control" value={pass}
                                   onChange={(e) => {setPass(e.target.value)}}/>
                        </div>
                        <p className="text-danger">{warning}</p>
                        <button type="submit" className='btn btn-primary' onClick={onSubmit}>Promeni</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default PasswordChange