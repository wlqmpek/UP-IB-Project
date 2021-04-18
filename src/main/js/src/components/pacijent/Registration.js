import { useState } from "react"

const Registration = ({onAdd}) => {

    const [imeKorisnika, setIme] = useState('')
    const [prezimeKorisnika, setPrezime] = useState('')
    const [emailKorisnika, setEmail] = useState('')
    const [lozinkaKorisnika, setLozinka] = useState('')
    const [jbzo, setJbzo] = useState('')




    const onSubmit = (e) =>{
        e.preventDefault();

        if(!imeKorisnika || !prezimeKorisnika || !emailKorisnika || !lozinkaKorisnika || !jbzo ){
            alert('Sva polja su obavezna.')
            return
        }
        onAdd({imeKorisnika, prezimeKorisnika, emailKorisnika, lozinkaKorisnika, jbzo})

        setIme('')
        setPrezime('')
        setEmail('')
        setLozinka('')
        setJbzo('')
    }


    return (
        <div className='col-md-6'>
            <form>
                <div className='form-group'>
                    <label>Ime: </label>
                    <input type='text' value={imeKorisnika} onChange={e => setIme(e.target.value)} placeholder='Ime' className='form-control' />
                </div>
                <div className='form-group'>
                    <label>Prezime: </label>
                    <input type='text' value={prezimeKorisnika} onChange={e => setPrezime(e.target.value)} placeholder='Prezime' className='form-control' />
                </div>
                <div className='form-group'>
                    <label>Email: </label>
                    <input type='email' value={emailKorisnika} onChange={e => setEmail(e.target.value)} placeholder='Email' className='form-control' />
                </div>
                <div className='form-group'>
                    <label>Lozinka: </label>
                    <input type='password' value={lozinkaKorisnika} onChange={e => setLozinka(e.target.value)} placeholder='Lozinka' className='form-control' />
                </div>
                <div className='form-group'>
                    <label>JBZO: </label>
                    <input type='text' value={jbzo} onChange={e => setJbzo(e.target.value)} placeholder='JBZO' className='form-control'></input>
                </div>
                <button type='submit' className='btn btn-primary' onClick={onSubmit}>Registruj se</button>
            </form>
        </div>
    )
}

export default Registration
