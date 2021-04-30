import { useState } from "react"
import { PacientService } from "../../services/PacientService"
import validator from 'validator'

const Registration = () => {

    const [imeKorisnika, setIme] = useState('')
    const [prezimeKorisnika, setPrezime] = useState('')
    const [emailKorisnika, setEmail] = useState('')
    const [lozinkaKorisnika, setLozinka] = useState('')
    const [ponovljenaLozinka, setPonovljena] = useState('')
    const [jbzo, setJbzo] = useState('')
    const [pacijenti, setPacijenti] = useState([])

    const pacijent = {
        imeKorisnika: imeKorisnika,
        prezimeKorisnika: prezimeKorisnika,
        emailKorisnika: emailKorisnika,
        lozinkaKorisnika: lozinkaKorisnika,
        jbzo: jbzo
    }

    async function fetchPacients(){
        try {
            const response = await PacientService.getPacients()
            setPacijenti(response.data)
        } catch (error){
            console.error(`Greska ${error}`)
        }
    }

    async function addPacient() {
        try {
            await PacientService.createPacient(pacijent)

        } catch (error){
            console.error(`Greska ${error}`)
        }
    }

    const onSubmit = (e) =>{
        e.preventDefault();

        if(!imeKorisnika || !prezimeKorisnika || !emailKorisnika || !lozinkaKorisnika || !jbzo || !ponovljenaLozinka){
            alert('Sva polja su obavezna.')
            return
        }

        if(lozinkaKorisnika.valueOf() !== ponovljenaLozinka.valueOf()){
            alert('Ponovljena lozinka nije ista kao uneta lozinka')
            return
        }

        if(isNaN(jbzo)){
            alert('Jbzo mora sadrzat samo cifre')
            return
        }

        if(!validator.isEmail(emailKorisnika)){
            alert('Email nije validan')
            return
        }

        addPacient()

        setIme('')
        setPrezime('')
        setEmail('')
        setLozinka('')
        setPonovljena('')
        setJbzo('')
    }


    return (
        <div style={{ margin: "50px" }}>
            <div className="container">
                <div className="row">
                    <div className='card col-md-6 offset-md-3 offset-md-3'>
                        <h3 style={{ margin: "10px", textDecoration: "underline" }} className="text-center">Registracija</h3>
                        <div className="card-body">
                            <form>
                                <div className='form-group'>
                                    <label>Ime: </label>
                                    <input type='text' value={imeKorisnika} onChange={e => setIme(e.target.value)} placeholder='Ime' className='form-control' />
                                    <br />
                                    <label>Prezime: </label>
                                    <input type='text' value={prezimeKorisnika} onChange={e => setPrezime(e.target.value)} placeholder='Prezime' className='form-control' />
                                    <br />
                                    <label>Email: </label>
                                    <input type='email' value={emailKorisnika} onChange={e => setEmail(e.target.value)} placeholder='Email' className='form-control' />
                                    <br />
                                    <label>Lozinka: </label>
                                    <input type='password' value={lozinkaKorisnika} onChange={e => setLozinka(e.target.value)} placeholder='Lozinka' className='form-control' />
                                    <br />
                                    <label>Ponovljena lozinka: </label>
                                    <input type='password' value={ponovljenaLozinka} onChange={e => setPonovljena(e.target.value)} placeholder='Ponovljena lozinka' className='form-control' />
                                    <br />
                                    <label>JBZO: </label>
                                    <input type='text' value={jbzo} onChange={e => setJbzo(e.target.value)} placeholder='JBZO' className='form-control'></input>
                                </div>
                                <button type='submit' className='btn btn-primary' onClick={onSubmit}>Registruj se</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Registration
