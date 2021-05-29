package com.projekat.UPIB.web.dto;

import com.projekat.UPIB.models.Klinika;

public class KlinikaListaDTO {

    private Long id;
    private String label;

    public KlinikaListaDTO(){

    }

    public KlinikaListaDTO(Klinika klinika){

        this.id = klinika.getIdKlinike();
        this.label = klinika.getNaziv();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
