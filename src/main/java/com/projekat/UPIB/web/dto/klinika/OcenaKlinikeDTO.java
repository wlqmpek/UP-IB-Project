package com.projekat.UPIB.web.dto.klinika;

public class OcenaKlinikeDTO {

    private Long idPacijenta;
    private Long idKlinike;
    private int ocena;

    public OcenaKlinikeDTO() {
    }

    public OcenaKlinikeDTO(Long idPacijenta, Long idKlinike, int ocena) {
        this.idPacijenta = idPacijenta;
        this.idKlinike = idKlinike;
        this.ocena = ocena;
    }

    public Long getIdPacijenta() {
        return idPacijenta;
    }

    public void setIdPacijenta(Long idPacijenta) {
        this.idPacijenta = idPacijenta;
    }

    public Long getIdKlinike() {
        return idKlinike;
    }

    public void setIdKlinike(Long idKlinike) {
        this.idKlinike = idKlinike;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
