package com.projekat.UPIB.dto;

public class PacijentLoginDTO {

    private String emailKorisnika;
    private String lozinkaKorisnika;

    public  PacijentLoginDTO() {
        super();
    }

    public PacijentLoginDTO(String email, String lozinka) {
        this.emailKorisnika = emailKorisnika;
        this.lozinkaKorisnika = lozinka;
    }

    public String getEmailKorisnika() {
        return emailKorisnika;
    }

    public void setEmailKorisnika(String email) {
        this.emailKorisnika = email;
    }

    public String getLozinkaKorisnika() {
        return lozinkaKorisnika;
    }

    public void setLozinkaKorisnika(String lozinka) {
        this.lozinkaKorisnika = lozinka;
    }
}
