import 'bootstrap/dist/css/bootstrap.min.css'
import { useState } from 'react';
import CreateClinic from '../components/klinika/Create'

function CreateClinicsService() {

    const [klinike, setKlinike] = useState([])

    const onAdd = async (klinika) => {
        const res = await fetch('http://localhost:8080/KlinickiCentar/Klinike',
            {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(klinika)
            })

        const data = await res.json()

        setKlinike(data)
    }

    return (
        <div className="container">
            <CreateClinic onAdd={onAdd} />
        </div>
    );
}

export default CreateClinicsService;
