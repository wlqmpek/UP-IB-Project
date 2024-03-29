import {useEffect, useState} from "react";
import DateTimePicker from 'react-datetime-picker'
import Select from "react-dropdown-select";
import {MedicinskaSestraService} from "../../services/MedicinskaSestraService";
import PreglediService from "../../services/PreglediService";

const CreateFreeAppointment = () => {

    const [pocetak, setPocetak] = useState(new Date())
    const [kraj, setKraj] = useState(new Date())
    const [appointment, setAppointment] = useState({
        pocetakTermina: new Date(),
        krajTermina: new Date(),
        MSestra: "",
        cena: 0.0,
    })
    const [selectedMSestra, setSelectedMSestra] = useState(null)
    const [selectedCena, setSelectedCena] = useState(null)
    const [warning, setWarning] = useState("")
    const [msestre, setMSestre] = useState([])
    const [priceList, setPriceList] = useState([])

    async function fetchMSestre() {
        try {
            const response = await MedicinskaSestraService.getMSestreForList()
            setMSestre(response.data)
        }catch (e) {
            console.error(e)
        }
    }

    async function createAppointment() {
        try {
            await PreglediService.createPregledLekar(appointment)
        } catch (e) {
            console.error(e)
        }
    }

    useEffect(()=>{
        fetchMSestre()
    },[])

    const handleChangeMSestra = selectedOption => {
        setSelectedMSestra({ selectedOption });
        setAppointment({...appointment, ["MSestra"]: selectedOption[0].value})
    };

    const handleChangeCena = selectedOption => {
        setSelectedCena({ selectedOption });
        setAppointment({...appointment, ["cena"]: selectedOption[0].value})
    };

    const onSubmit = (e) =>{
        e.preventDefault()

        if(!appointment.cena || !appointment.krajTermina || !appointment.pocetakTermina || !appointment.MSestra){
            setWarning("Sve mora da bude izabrano")
            return
        }

        //createAppointment()
        console.log("Ovo je pregled: " + appointment)
    }

    return(
        <div className="container-fluid" style={{marginTop: 100}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Kreiranje termina pregleda</h3>
                    <form>
                        <div className="form-group">
                            <label>Pocetak termina</label>
                            <DateTimePicker value={pocetak} onChange={setPocetak}/>
                        </div>
                        <div className="form-group">
                            <label>Kraj termina</label>
                            <DateTimePicker value={kraj} onChange={setKraj}/>
                        </div>
                        <div className="form-group">
                            <label>Medicinska sestra</label>
                            <Select options={msestre} onChange={handleChangeMSestra}/>
                        </div>
                        <div className="form-group">
                            <label>Cena</label>
                            <Select options={priceList} onChange={handleChangeCena}/>
                        </div>
                        <p>{warning}</p>
                        <button type="submit" className="btn btn-primary" onClick={onSubmit}>Kreiraj</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default CreateFreeAppointment