import AxiosClient from "./clients/AxiosClient";

export const CenovnikService = {

    getPriceListL,
    getPriceList,
    postPriceList,
    putPriceList,
    deletePriceList
}

async function getPriceList() {
    return await AxiosClient.get("cenovnik")
}

async function postPriceList(priceList){
    return await AxiosClient.post("cenovnik", priceList)
}

async function putPriceList(id, priceList){
    return await AxiosClient.put(`/${id}`, priceList)
}

async function getPriceListL() {
    return await AxiosClient.get("cenovnik/lista")
}

async function deletePriceList(id) {
    return await AxiosClient.delete(`/${id}`)
}