package com.projekat.UPIB.web.dto.pacijent;

import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.models.PacijentLink;

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

    public PacijentLinkDTO(PacijentLink pacijentLink){
        this.putanja = pacijentLink.getPutanja();
        this.valid = pacijentLink.isValid();
        this.datumIsteka = pacijentLink.getDatumIsteka();
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
