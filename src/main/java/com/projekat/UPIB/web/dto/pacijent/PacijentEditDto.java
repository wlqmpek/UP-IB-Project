package com.projekat.UPIB.web.dto.pacijent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class PacijentEditDto implements Serializable {

    @JsonProperty(value = "imeKorisnika")
    private String ime;
    @JsonProperty(value = "prezimeKorisnika")
    private String prezime;
    @JsonProperty(value = "lozinkaKorisnika")
    private String lozinka;
    @JsonProperty(value = "ponovljenaLozinkaKorisnika")
    private String ponovljenaLozinka;

    public PacijentEditDto() { super(); }


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

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPonovljenaLozinka() {
        return ponovljenaLozinka;
    }

    public void setPonovljenaLozinka(String ponovljenaLozinka) {
        this.ponovljenaLozinka = ponovljenaLozinka;
    }
}
