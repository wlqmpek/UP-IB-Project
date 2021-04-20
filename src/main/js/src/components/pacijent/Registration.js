import { useState } from "react"

const Registration = () => {

    const [imeKorisnika, setIme] = useState('')
    const [prezimeKorisnika, setPrezime] = useState('')
    const [emailKorisnika, setEmail] = useState('')
    const [lozinkaKorisnika, setLozinka] = useState('')
    const [jbzo, setJbzo] = useState('')
    const [pacijenti, setPacijenti] = useState([])

    function PostPacijent(imeKorisnika, prezimeKorisnika, emailKorisnika, lozinkaKorisnika, jbzo) {

        const pacijent = {
            imeKorisnika: imeKorisnika,
            prezimeKorisnika: prezimeKorisnika,
            emailKorisnika: emailKorisnika,
            lozinkaKorisnika: lozinkaKorisnika,
            jbzo: jbzo
        }
        console.log(imeKorisnika)
        const onAdd = async (pacijent) => {
        const res = await fetch('http://localhost:8080/KlinickiCentar/Pacijenti',
        {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(pacijent)
        })
  
          const data = await res.json()
  
          setPacijenti(data)
        }
        onAdd(pacijent)
        return pacijenti
    }
    

    const onSubmit = (e) =>{
        e.preventDefault();

        if(!imeKorisnika || !prezimeKorisnika || !emailKorisnika || !lozinkaKorisnika || !jbzo ){
            alert('Sva polja su obavezna.')
            return
        }
        PostPacijent(imeKorisnika, prezimeKorisnika, emailKorisnika, lozinkaKorisnika, jbzo)

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
