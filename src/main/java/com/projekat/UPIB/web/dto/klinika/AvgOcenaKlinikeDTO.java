package com.projekat.UPIB.web.dto.klinika;

public class AvgOcenaKlinikeDTO {

    private String naziv;
    private Double ocena;

    public AvgOcenaKlinikeDTO() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }
}
