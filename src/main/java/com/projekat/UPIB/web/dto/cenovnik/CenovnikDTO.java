package com.projekat.UPIB.web.dto.cenovnik;

import com.projekat.UPIB.models.Cenovnik;

public class CenovnikDTO {

    private Long id;
    private String naziv;
    private Double cena;
    private Long idKlinike;

    public CenovnikDTO() {
    }

    public CenovnikDTO(Cenovnik cenovnik) {

        this.id = cenovnik.getId();
        this.cena = cenovnik.getCena();
        this.idKlinike = cenovnik.getKlinika().getIdKlinike();
        this.naziv = cenovnik.getNaziv();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Long getIdKlinike() {
        return idKlinike;
    }

    public void setIdKlinike(Long idKlinike) {
        this.idKlinike = idKlinike;
    }
}
