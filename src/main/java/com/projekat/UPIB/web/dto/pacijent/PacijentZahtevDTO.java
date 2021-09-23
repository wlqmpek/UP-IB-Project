package com.projekat.UPIB.web.dto.pacijent;

import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;

public class PacijentZahtevDTO {

    private long id;
    private String ime;
    private String prezime;
    private String email;
    private StatusKorisnika statusKorisnika;

    public PacijentZahtevDTO() {
    }

    public PacijentZahtevDTO(Pacijent pacijent) {
        super();
        this.id = pacijent.getIdKorisnika();
        this.ime = pacijent.getImeKorisnika();
        this.prezime = pacijent.getPrezimeKorisnika();
        this.email = pacijent.getEmailKorisnika();
        this.statusKorisnika = pacijent.getStatusKorisnika();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusKorisnika getStatusKorisnika() {
        return statusKorisnika;
    }

    public void setStatusKorisnika(StatusKorisnika statusKorisnika) {
        this.statusKorisnika = statusKorisnika;
    }
}
