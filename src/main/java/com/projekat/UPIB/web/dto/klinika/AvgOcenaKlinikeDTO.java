package com.projekat.UPIB.web.dto.klinika;

public class AvgOcenaKlinikeDTO {

    // Dodao idKlinike da bih olaksao sebi posao na frontu, inace, generalno ovaj
    // dto uopste nema smisla i moze se integrisati u drugi KlinikaFrontDto al jbg. - WLQ
    private Long idKlinike;
    private String naziv;
    private Double ocena;

    public AvgOcenaKlinikeDTO() {
    }

    public Long getIdKlinike() {
        return idKlinike;
    }

    public void setIdKlinike(Long idKlinike) {
        this.idKlinike = idKlinike;
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
