package com.projekat.UPIB.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;

public class PacijentFrontDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String JBZO;
    private Long idZdravstvenogKartona;
    private StatusKorisnika statusKorisnika;

    public PacijentFrontDTO() {
        super();
    }

    public PacijentFrontDTO(Long id, String ime, String prezime, String email, String jBZO,
    		Long idZdravstvenogKartona, StatusKorisnika statusKorisnika) {
        super();
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.JBZO = jBZO;
        this.idZdravstvenogKartona = idZdravstvenogKartona;
        this.statusKorisnika = statusKorisnika;
    }

    public PacijentFrontDTO(Pacijent pacijent) {
        super();
        this.id = pacijent.getIdKorisnika();
        this.ime = pacijent.getImeKorisnika();
        this.prezime = pacijent.getPrezimeKorisnika();
        this.email = pacijent.getEmailKorisnika();
        this.JBZO = pacijent.getJBZO();
        this.idZdravstvenogKartona = pacijent.getZdravstveniKarton().getIdZdravstvenogKartona();
        this.statusKorisnika = pacijent.getStatusKorisnika();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getJBZO() {
        return JBZO;
    }

    public void setJBZO(String jBZO) {
        JBZO = jBZO;
    }

    public Long getIdZdravstvenogKartona() {
        return idZdravstvenogKartona;
    }

    public void setIdZdravstvenogKartona(Long idZdravstvenogKartona) {
        this.idZdravstvenogKartona = idZdravstvenogKartona;
    }

    public StatusKorisnika getStatusKorisnika() {
        return statusKorisnika;
    }

    public void setStatusKorisnika(StatusKorisnika statusKorisnika) {
        this.statusKorisnika = statusKorisnika;
    }

}
