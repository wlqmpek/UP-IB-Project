import {useState} from "react"
import {PacijentService} from "../../services/PacijentService"
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
        ime: imeKorisnika,
        prezime: prezimeKorisnika,
        email: emailKorisnika,
        lozinka: lozinkaKorisnika,
        jbzo: jbzo
    }

    async function fetchPacijents() {
        try {
            const response = await PacijentService.getPacijents()
            setPacijenti(response.data)
        } catch (error) {
            console.error(`Greska ${error}`)
        }
    }

    async function addPacijent() {
        try {
            await PacijentService.createPacijent(pacijent)

        } catch (error) {
            console.error(`Greska ${error}`)
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();

        if (!imeKorisnika || !prezimeKorisnika || !emailKorisnika || !lozinkaKorisnika || !jbzo || !ponovljenaLozinka) {
            alert('Sva polja su obavezna.')
            return
        }

        if (lozinkaKorisnika.valueOf() !== ponovljenaLozinka.valueOf()) {
            alert('Ponovljena lozinka nije ista kao uneta lozinka')
            return
        }

        if (isNaN(jbzo)) {
            alert('Jbzo mora sadrzat samo cifre')
            return
        }

        if (!validator.isEmail(emailKorisnika)) {
            alert('Email nije validan')
            return
        }

        addPacijent()

        setIme('')
        setPrezime('')
        setEmail('')
        setLozinka('')
        setPonovljena('')
        setJbzo('')
    }


    return (
        <div className="container">
            <div className="row">
                <div className='col-md-6 offset-md-3 offset-md-3'>
                    <h3 className="text-center">Registracija</h3>
                    <form>
                        <div className='form-group'>
                            <label>Ime: </label>
                            <input type='text' value={imeKorisnika} onChange={e => setIme(e.target.value)}
                                   placeholder='Ime' className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Prezime: </label>
                            <input type='text' value={prezimeKorisnika} onChange={e => setPrezime(e.target.value)}
                                   placeholder='Prezime' className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Email: </label>
                            <input type='email' value={emailKorisnika} onChange={e => setEmail(e.target.value)}
                                   placeholder='Email' className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Lozinka: </label>
                            <input type='password' value={lozinkaKorisnika} onChange={e => setLozinka(e.target.value)}
                                   placeholder='Lozinka' className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>Ponovljena lozinka: </label>
                            <input type='password' value={ponovljenaLozinka}
                                   onChange={e => setPonovljena(e.target.value)} placeholder='Ponovljena lozinka'
                                   className='form-control'/>
                        </div>
                        <div className='form-group'>
                            <label>JBZO: </label>
                            <input type='text' value={jbzo} onChange={e => setJbzo(e.target.value)} placeholder='JBZO'
                                   className='form-control'></input>
                        </div>
                        <button type='submit' className='btn btn-primary' onClick={onSubmit}>Registruj se</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default Registration
