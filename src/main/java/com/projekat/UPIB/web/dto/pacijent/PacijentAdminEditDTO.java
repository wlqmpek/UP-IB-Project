package com.projekat.UPIB.web.dto.pacijent;

import com.projekat.UPIB.models.Pacijent;

public class PacijentAdminEditDTO {

    private long id;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private String JBZO;

    public PacijentAdminEditDTO(){}

    public PacijentAdminEditDTO(long id, String ime, String prezime, String email, String lozinka, String JBZO) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.JBZO = JBZO;
    }

    public PacijentAdminEditDTO(Pacijent pacijent){
        this.id = pacijent.getIdKorisnika();
        this.ime = pacijent.getImeKorisnika();
        this.prezime = pacijent.getPrezimeKorisnika();
        this.email = pacijent.getEmailKorisnika();
        this.lozinka = pacijent.getLozinkaKorisnika();
        this.JBZO = pacijent.getJBZO();
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

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getJBZO() {
        return JBZO;
    }

    public void setJBZO(String JBZO) {
        this.JBZO = JBZO;
    }
}
