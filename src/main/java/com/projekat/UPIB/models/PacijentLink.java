package com.projekat.UPIB.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "pacijent_link")
public class PacijentLink {

    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "putanja", nullable = false, unique = true)
    private String putanja;

    @Column(name = "valid", nullable = false, unique = false)
    private boolean valid;

    @Column(name = "datum_isteka", nullable = false, unique = false)
    private LocalDateTime datumIsteka;

    public PacijentLink() {}

    public PacijentLink(String email, String putanja, boolean valid, LocalDateTime datumIsteka) {
        this.email = email;
        this.putanja = putanja;
        this.valid = valid;
        this.datumIsteka = datumIsteka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public LocalDateTime getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(LocalDateTime datumIsteka) {
        this.datumIsteka = datumIsteka;
    }
}
