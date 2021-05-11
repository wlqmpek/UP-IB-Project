package com.projekat.UPIB.dto;

public class KorisnikLoginDTO {

    private String emailKorisnika;
    private String lozinkaKorisnika;

    public KorisnikLoginDTO() { super(); }

    public KorisnikLoginDTO(String emailKorisnika, String lozinkaKorisnika) {
        this.emailKorisnika = emailKorisnika;
        this.lozinkaKorisnika = lozinkaKorisnika;
    }

    public String getEmailKorisnika() {
        return emailKorisnika;
    }

    public void setEmailKorisnika(String emailKorisnika) {
        this.emailKorisnika = emailKorisnika;
    }

    public String getLozinkaKorisnika() {
        return lozinkaKorisnika;
    }

    public void setLozinkaKorisnika(String lozinkaKorisnika) {
        this.lozinkaKorisnika = lozinkaKorisnika;
    }
}
