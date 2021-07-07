package com.projekat.UPIB.web.dto.cenovnik;

import com.projekat.UPIB.models.Cenovnik;

public class CenovnikListaDTO {

    private Double value;
    private String label;

    public CenovnikListaDTO() {
    }

    public CenovnikListaDTO(Cenovnik cenovnik){
        this.label = cenovnik.getNaziv() + " " + cenovnik.getCena();
        this.value = cenovnik.getCena();
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
