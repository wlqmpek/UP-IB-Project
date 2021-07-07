import React, {useState} from "react";
import {CenovnikService} from "../../services/CenovnikService";
import {useHistory} from "react-router";

const AddPriceList = () => {

    const history = useHistory()
    const [warning, setWarning] = useState("")
    const [priceList, setPriceList] = useState({
        naziv: "",
        cena: 0.0
    })

    async function addPriceList(){
        try {
            await CenovnikService.postPriceList(priceList)
        } catch (e) {
            console.error(e)
        }
    }

    const handleFormInputChange = (name) => (event) =>{
        const val = event.target.value
        setPriceList({...priceList, [name]: val })
    }

    const submit = (e) => {
        e.preventDefault()

        if(!priceList.cena || !priceList.naziv){
            setWarning("Sva polja moraju biti popunjena")
            return
        }

        addPriceList()
        history.push("/cenovnik")
    }

    return(
        <div className="container" style={{marginTop: "100px"}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Dodaj uslugu</h3>
                    <div className='form-group'>
                        <label>Naziv: </label>
                        <input type='text' value={priceList.naziv}  onChange={handleFormInputChange("naziv")}
                               className='form-control'/>
                    </div>
                    <div className='form-group'>
                        <label>Cena </label>
                        <input type='number' value={priceList.cena} onChange={handleFormInputChange("cena")}
                               className='form-control'/>
                    </div>
                    <p className="text-danger">{warning}</p>
                    <button className="btn btn-primary" onClick={submit}>Dodaj</button>
                </div>
            </div>
        </div>
    )
}

export default AddPriceList