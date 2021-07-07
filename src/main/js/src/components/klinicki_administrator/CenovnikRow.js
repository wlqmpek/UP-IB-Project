import {useHistory} from "react-router";
import {CenovnikService} from "../../services/CenovnikService";

const CenovnikRow = ({cenovnik, setPriceList, priceList}) => {

    const history = useHistory()


    async function removePriceList() {
        try {
            await CenovnikService.deletePriceList(cenovnik.id)
            setPriceList(priceList.filter((p)=> p.id !== cenovnik.id))
        } catch (e) {
            console.error(e)
        }
    }

    const edit = () => {
        history.push(`/cenovnik/${cenovnik.id}`)
    }

    const remove = () => {
        removePriceList()
    }

    return(
        <tr>
            <td>{cenovnik.naziv}</td>
            <td>{cenovnik.cena}</td>
            <td><button onClick={edit} className="btn btn-warning">Izmeni</button></td>
            <td><button onClick={remove} className="btn btn-danger">Obrisi</button></td>
        </tr>
    )
}

export default CenovnikRow