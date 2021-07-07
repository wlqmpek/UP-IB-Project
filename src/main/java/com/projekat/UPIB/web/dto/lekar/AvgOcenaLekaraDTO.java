package com.projekat.UPIB.web.dto.lekar;

public class AvgOcenaLekaraDTO {

    private String ime;
    private String prezime;
    private Double ocena;

    public AvgOcenaLekaraDTO() {
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }
}
