package com.projekat.UPIB.repositories;

import com.projekat.UPIB.models.PacijentLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacijentLinkRepozitorijum extends JpaRepository<PacijentLink, String> {

    PacijentLink findByPutanja(String putanja);
}
