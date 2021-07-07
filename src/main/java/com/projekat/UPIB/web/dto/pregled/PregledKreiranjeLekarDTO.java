package com.projekat.UPIB.web.dto.pregled;

import java.time.LocalDateTime;

public class PregledKreiranjeLekarDTO {

    private LocalDateTime pocetakTermina;
    private LocalDateTime krajTermina;
    private String medSestraEmail;
    private Double cena;
    private Long idKlinike;

    public PregledKreiranjeLekarDTO() {
    }

    public LocalDateTime getPocetakTermina() {
        return pocetakTermina;
    }

    public void setPocetakTermina(LocalDateTime pocetakTermina) {
        this.pocetakTermina = pocetakTermina;
    }

    public LocalDateTime getKrajTermina() {
        return krajTermina;
    }

    public void setKrajTermina(LocalDateTime krajTermina) {
        this.krajTermina = krajTermina;
    }

    public String getMedSestraEmail() {
        return medSestraEmail;
    }

    public void setMedSestraEmail(String medSestraEmail) {
        this.medSestraEmail = medSestraEmail;
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
