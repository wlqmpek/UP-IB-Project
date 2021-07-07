import {useParams} from "react-router";
import {useEffect, useState} from "react";
import PreglediService from "../../services/PreglediService";
import DateTimePicker from "react-datetime-picker";
import Select from "react-dropdown-select";
import {MedicinskaSestraService} from "../../services/MedicinskaSestraService";
import LekarService from "../../services/LekarService";

const UpdateAppointment = () => {

    const [pocetak, setPocetak] = useState(new Date())
    const [kraj, setKraj] = useState(new Date())
    const [pregled, setPregled] = useState({})
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

    async function getPregled() {
        try {
             const response = await PreglediService.getPregled(id)
            setPregled(response.data)
        }catch (e) {
            console.error(e)
        }
    }

    async function edit() {
        try {
            await PreglediService.editPregled(id, pregled)
        } catch (e){
            console.error(e)
        }
    }

    useEffect(()=>{
        getPregled()
        fetchLekari()
        fetchMSestre()
    },[])

    useEffect(()=>{
        setPocetak(pregled.pocetakTermina)
        setKraj(pregled.krajTermina)
    }, [pregled])

    const handleChangeMSestra = selectedOption => {
        setSelectedMSestra({ selectedOption });
        setPregled({...pregled, ["MSestra"]: selectedOption[0].value})
    };

    const handleChangeCena = selectedOption => {
        setSelectedCena({ selectedOption });
        setPregled({...pregled, ["cena"]: selectedOption[0].value})
    };

    const handleChangeLekar = selectedOption => {
        setSelectedLekar({ selectedOption });
        setPregled({...pregled, ["lekar"]: selectedOption[0].value})
    };

    const onSubmit = (e) =>{
        e.preventDefault()

        if(!pregled.cena || !pregled.krajTermina || !pregled.pocetakTermina || !pregled.MSestra){
            setWarning("Sve mora da bude izabrano")
            return
        }

        //edit()
        console.log("Ovo je pregled: " + pregled)
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
                            <label>Lekar</label>
                            <Select options={lekari} onChange={handleChangeLekar}/>
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

export default UpdateAppointment