package com.projekat.UPIB.web.dto.pacijent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.web.dto.ZdravstveniKartonDTO;

public class PacijentFrontDTO {

    private long id;
    private String ime;
    private String prezime;
    private String email;
    private String JBZO;
    private long idZdravstvenogKartona;

    public PacijentFrontDTO() {
        super();
    }

    public PacijentFrontDTO(long id, String ime, String prezime, String email, String jBZO,
                               long idZdravstvenogKartona) {
        super();
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.JBZO = jBZO;
        this.idZdravstvenogKartona = idZdravstvenogKartona;
    }

    public PacijentFrontDTO(Pacijent pacijent) {
        super();
        this.id = pacijent.getIdKorisnika();
        this.ime = pacijent.getImeKorisnika();
        this.prezime = pacijent.getPrezimeKorisnika();
        this.email = pacijent.getEmailKorisnika();
        this.JBZO = pacijent.getJBZO();
        this.idZdravstvenogKartona = pacijent.getZdravstveniKarton().getIdZdravstvenogKartona();
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

    public String getJBZO() {
        return JBZO;
    }

    public void setJBZO(String jBZO) {
        JBZO = jBZO;
    }

    public long getIdZdravstvenogKartona() {
        return idZdravstvenogKartona;
    }

    public void setIdZdravstvenogKartona(long idZdravstvenogKartona) {
        this.idZdravstvenogKartona = idZdravstvenogKartona;
    }
}
