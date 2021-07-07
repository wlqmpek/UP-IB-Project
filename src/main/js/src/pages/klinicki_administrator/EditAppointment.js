import {useEffect, useState} from "react";
import {useParams} from "react-router";
import PreglediService from "../../services/PreglediService";
import DateTimePicker from "react-datetime-picker";
import Select from "react-dropdown-select";
import {MedicinskaSestraService} from "../../services/MedicinskaSestraService";
import LekarService from "../../services/LekarService";
import {CenovnikService} from "../../services/CenovnikService";

const EditAppointment = () => {

    const [pocetak, setPocetak] = useState(new Date())
    const [kraj, setKraj] = useState(new Date())
    const [appointment, setAppointment] = useState({})
    const {id} = useParams()
    const [selectedMSestra, setSelectedMSestra] = useState(null)
    const [selectedCena, setSelectedCena] = useState(null)
    const [selectedLekar, setSelectedLekar] = useState(null)
    const [warning, setWarning] = useState("")
    const [msestre, setMSestre] = useState([])
    const [priceList, setPriceList] = useState([])
    const [lekari, setLekari] = useState([])

    async function fetchMSestre() {
        try {
            const response = await MedicinskaSestraService.getMSestreForList()
            setMSestre(response.data)
        }catch (e) {
            console.error(e)
        }
    }

    async function fetchLekari() {
        try {
            const response = await LekarService.getLekariList()
            setLekari(response.data)
        } catch (e) {
            console.error(e)
        }
    }

    async function fetchPriceList() {
        try {
            const response = await CenovnikService.getPriceListL()
            setPriceList(response.data)
        } catch (e){
            console.error(e)
        }
    }
    async function getAppointment() {
        try {
            const response = await PreglediService.getPregled(id)
            setAppointment(response.data)
        } catch (e){
            console.error(e)
        }
    }

    async function edit() {
        try {
            await PreglediService.editPregled(id, appointment)
        } catch (e) {
            console.error(e)
        }
    }

    const handleChangeMSestra = selectedMSestra => {
        setSelectedMSestra({ selectedMSestra });
        setAppointment({...appointment, ["medSestraEmail"]: selectedMSestra[0].value})
    };

    const handleChangeCena = selectedCena => {
        setSelectedCena({ selectedCena });
        setAppointment({...appointment, ["cena"]: selectedCena[0].value})
    };

    const handleChangeLekar = selectedLekar => {
        setSelectedLekar({ selectedLekar });
        setAppointment({...appointment, ["lekarEmail"]: selectedLekar[0].value})
    };

    useEffect(()=>{
        getAppointment()
        fetchMSestre()
        fetchPriceList()
        fetchLekari()
    },[])

    useEffect(()=>{
        setKraj(appointment.krajTermina)
        setPocetak(appointment.pocetakTermina)
    },[appointment])



    const submit = (e) => {
        e.preventDefault()
        if(!appointment.cena || !appointment.krajTermina || !appointment.pocetakTermina || !appointment.medSestraEmail){
            setWarning("Sve mora da bude izabrano")
            return
        }
        edit()

    }

    return(
        <div className="container-fluid" style={{marginTop: 100}}>
            <div className="row">
                <div className="col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-center">Izmena termina pregleda</h3>
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
                            <Select options={msestre} valueField={selectedMSestra} onChange={handleChangeMSestra}/>
                        </div>
                        <div className="form-group">
                            <label>Lekar</label>
                            <Select options={lekari} valueField={selectedLekar} onChange={handleChangeLekar}/>
                        </div>
                        <div className="form-group">
                            <label>Cena</label>
                            <Select options={priceList}  valueField={selectedCena} onChange={handleChangeCena}/>
                        </div>
                        <p className="text-danger">{warning}</p>
                        <button type="submit" className="btn btn-primary" onClick={submit}>Kreiraj</button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default EditAppointment