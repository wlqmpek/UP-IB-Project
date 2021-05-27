package com.projekat.UPIB.web.dto;

import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString
public class KorisnikLoginDTO {

    @NotEmpty
    private String emailKorisnika;
    @NotEmpty
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
