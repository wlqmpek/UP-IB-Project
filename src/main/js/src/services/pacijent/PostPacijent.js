import { useState } from 'react'
import PacijentRegistration from '../../components/pacijent/Registration'

function PostPacijent() {

    const [pacijenti, setPacijenti] = useState([])

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

    return (
        <div>
            <PacijentRegistration onAdd={PostPacijent}/>
        </div>
    );
    
}

export default PostPacijent;