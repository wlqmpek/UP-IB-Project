import { useState } from "react"

const CreateClinic = ({ onAdd }) => {

    const [naziv, setNaziv] = useState('')
    const [opis, setOpis] = useState('')
    const [adresa, setAdresa] = useState('')




    const onSubmit = (e) => {
        e.preventDefault();

        if (!naziv || !opis || !adresa) {
            alert('Sva polja su obavezna.')
            return
        }
        onAdd({ naziv, opis, adresa})

        setNaziv('')
        setOpis('')
        setAdresa('')
    }


    return (
        <div className='col-md-6'>
            <form>
                <div className='form-group'>
                    <label>Naziv: </label>
                    <input type='text' value={naziv} onChange={e => setNaziv(e.target.value)} placeholder='Naziv' className='form-control' />
                </div>
                <div className='form-group'>
                    <label>Opis: </label>
                    <input type='text' value={opis} onChange={e => setOpis(e.target.value)} placeholder='Opis' className='form-control' />
                </div>
                <div className='form-group'>
                    <label>Adresa: </label>
                    <input type='text' value={adresa} onChange={e => setAdresa(e.target.value)} placeholder='Adresa' className='form-control' />
                </div>
                <button type='submit' className='btn btn-primary' onClick={onSubmit}>Submit</button>
            </form>
        </div>
    )
}

export default CreateClinic
