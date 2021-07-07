package com.projekat.UPIB.web.dto.lekar;

public class OcenaLekaraDTO {

    private Long idPacijenta;
    private Long idLekara;
    private int ocena;

    public OcenaLekaraDTO() {
    }

    public OcenaLekaraDTO(Long idPacijenta, Long idLekara, int ocena) {
        this.idPacijenta = idPacijenta;
        this.idLekara = idLekara;
        this.ocena = ocena;
    }

    public Long getIdPacijenta() {
        return idPacijenta;
    }

    public void setIdPacijenta(Long idPacijenta) {
        this.idPacijenta = idPacijenta;
    }

    public Long getIdLekara() {
        return idLekara;
    }

    public void setIdLekara(Long idLekara) {
        this.idLekara = idLekara;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
