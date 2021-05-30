package com.projekat.UPIB.web.dto.pacijent;

import java.time.LocalDateTime;

public class PacijentLinkDTO {

    private String putanja;
    private boolean valid;
    private LocalDateTime datumIsteka;

    public PacijentLinkDTO(){}

    public PacijentLinkDTO(String putanja, boolean valid, LocalDateTime datumIsteka) {
        this.putanja = putanja;
        this.valid = valid;
        this.datumIsteka = datumIsteka;
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
