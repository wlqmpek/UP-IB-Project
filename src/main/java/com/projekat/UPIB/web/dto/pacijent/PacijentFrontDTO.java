package com.projekat.UPIB.web.dto.pacijent;

import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.security.EnkripcijaDekripcijaUtils;
import org.springframework.beans.factory.annotation.Autowired;


public class PacijentFrontDTO {

    @Autowired
    private EnkripcijaDekripcijaUtils enkripcijaDekripcijaUtils;

    private long id;
    private String ime;
    private String prezime;
    private String email;
    private String JBZO;
    private StatusKorisnika statusKorisnika;
    private long idZdravstvenogKartona;

    public PacijentFrontDTO() {
        super();
    }

    public PacijentFrontDTO(long id, String ime, String prezime, String email, String jBZO, StatusKorisnika statusKorisnika,
                               long idZdravstvenogKartona) {
        super();
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.JBZO = jBZO;
        this.statusKorisnika = statusKorisnika;
        this.idZdravstvenogKartona = idZdravstvenogKartona;
    }

    public PacijentFrontDTO(Pacijent pacijent) {
        super();
        this.id = pacijent.getIdKorisnika();
        this.ime = pacijent.getImeKorisnika();
        this.prezime = pacijent.getPrezimeKorisnika();
        this.email = pacijent.getEmailKorisnika();
        this.JBZO = enkripcijaDekripcijaUtils.dekriptujJBZO(pacijent.getJBZO(), pacijent.getEmailKorisnika());
        this.statusKorisnika = pacijent.getStatusKorisnika();
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

    public StatusKorisnika getStatusKorisnika() {
        return statusKorisnika;
    }

    public void setStatusKorisnika(StatusKorisnika statusKorisnika) {
        this.statusKorisnika = statusKorisnika;
    }

    public long getIdZdravstvenogKartona() {
        return idZdravstvenogKartona;
    }

    public void setIdZdravstvenogKartona(long idZdravstvenogKartona) {
        this.idZdravstvenogKartona = idZdravstvenogKartona;
    }
}
