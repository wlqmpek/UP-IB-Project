package com.projekat.UPIB.web.dto.korisnik;

import com.projekat.UPIB.models.Korisnik;

public class KorisnikInfoDTO {

    private String ime;
    private String prezime;
    private String email;

    public KorisnikInfoDTO() {
    }

    public KorisnikInfoDTO(Korisnik korisnik){
        this.ime = korisnik.getImeKorisnika();
        this.prezime = korisnik.getPrezimeKorisnika();
        this.email = korisnik.getEmailKorisnika();
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
}
