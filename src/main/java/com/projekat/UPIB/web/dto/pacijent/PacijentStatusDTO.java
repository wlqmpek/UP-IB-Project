package com.projekat.UPIB.web.dto.pacijent;

import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;

public class PacijentStatusDTO {

    private long id;
    private StatusKorisnika statusKorisnika;

    public PacijentStatusDTO(){}

    public PacijentStatusDTO(long id, StatusKorisnika statusKorisnika) {
        this.id = id;
        this.statusKorisnika = statusKorisnika;
    }

    public PacijentStatusDTO(Pacijent pacijent){
        this.id = pacijent.getIdKorisnika();
        this.statusKorisnika = pacijent.getStatusKorisnika();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StatusKorisnika getStatusKorisnika() {
        return statusKorisnika;
    }

    public void setStatusKorisnika(StatusKorisnika statusKorisnika) {
        this.statusKorisnika = statusKorisnika;
    }
}
