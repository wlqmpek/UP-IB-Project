import {useEffect, useState} from "react";
import {RegistrationRequestService} from "../../services/RegistrationRequestService";

const AfterAcceptRegistration = () => {

    const [pacijentLink, setPacijentLink] = useState({})

    const url = window.location.href
    const parsedPath = url.slice(44, url.length)
    const path = parsedPath
    console.log(url)
    console.log(path)

    useEffect( ()=>{
        fetchLink()
        },[path]
    )

    if(pacijentLink.valid){
        setTimeout(function (){
            window.location.href = "http://localhost:3000/prijava"
            }, 5000
        )
    }
    async function fetchLink(){
        try {
            const response = await RegistrationRequestService.getLink(path)
            setPacijentLink(response.data)
        }catch (e){
            console.error(e)
        }
    }

    return(
        <div>
            <h1>Uspesno ste se registrovali na sistem</h1>
            <br/>
            <h3>Bicete prebaceni na stranicu za prijavu</h3>
        </div>
    )
}

export default AfterAcceptRegistration