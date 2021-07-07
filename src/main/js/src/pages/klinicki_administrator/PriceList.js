import {useEffect, useState} from "react";
import {CenovnikService} from "../../services/CenovnikService";
import CenovnikRow from "../../components/klinicki_administrator/CenovnikRow";
import {useHistory} from "react-router";

const PriceList = () => {

    const history = useHistory()
    const [priceList, setPriceList] = useState([])

    async function getPriceList() {
        try {
            const response = await CenovnikService.getPriceList()
            setPriceList(response.data)
        } catch (e){
            console.error(e)
        }
    }

    console.log(priceList)
    useEffect(()=>{
        getPriceList()
    },[])

    const add = () => {
        history.push("/cenovnik/dodaj")
    }

    return(
        <div className="table-responsive" style={{marginTop: "100px"}}>
            <h2 className="text-center">Cenovnik</h2>
            <table className="table table-striped table border">
                <thead>
                <tr>
                    <th>Naziv</th>
                    <th>Cena</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {priceList.map((cenovnik, index) =>
                    (<CenovnikRow key={index} cenovnik={cenovnik} priceList={priceList} setPriceList={setPriceList}/> ))}
                </tbody>
            </table>
            <button className="btn btn-primary" onClick={add}>Dodaj uslugu</button>
        </div>
    )
}

export default PriceList