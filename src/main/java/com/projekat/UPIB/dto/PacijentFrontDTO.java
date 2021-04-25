package com.projekat.UPIB.dto;

import com.projekat.UPIB.enums.StatusKorisnika;

public class PacijentFrontDTO {

    private long id;
    private String ime;
    private String prezime;
    private String email;
    private String JBZO;
    private ZdravstveniKartonDTO zdravstveniKarton;
    private StatusKorisnika statusKorisnika;

    public PacijentFrontDTO() {
        super();
    }

    public PacijentFrontDTO(long id, String ime, String prezime, String email, String jBZO,
                               ZdravstveniKartonDTO zdravstveniKarton, StatusKorisnika statusKorisnika) {
        super();
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.JBZO = jBZO;
        this.zdravstveniKarton = zdravstveniKarton;
        this.statusKorisnika = statusKorisnika;
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

    public ZdravstveniKartonDTO getZdravstveniKarton() {
        return zdravstveniKarton;
    }

    public void setZdravstveniKarton(ZdravstveniKartonDTO zdravstveniKarton) {
        this.zdravstveniKarton = zdravstveniKarton;
    }

    public StatusKorisnika getStatusKorisnika() {
        return statusKorisnika;
    }

    public void setStatusKorisnika(StatusKorisnika statusKorisnika) {
        this.statusKorisnika = statusKorisnika;
    }

}
