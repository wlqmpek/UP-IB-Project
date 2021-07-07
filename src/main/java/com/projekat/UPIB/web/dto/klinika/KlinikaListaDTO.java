package com.projekat.UPIB.web.dto.klinika;

import com.projekat.UPIB.models.Klinika;

public class KlinikaListaDTO {

    private Long value;
    private String label;

    public KlinikaListaDTO(){

    }

    public KlinikaListaDTO(Klinika klinika){

        this.value = klinika.getIdKlinike();
        this.label = klinika.getNaziv();
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
