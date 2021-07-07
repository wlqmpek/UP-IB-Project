package com.projekat.UPIB.services;

import com.projekat.UPIB.models.PacijentLink;

import java.util.List;

public interface IPacijentLinkService {

    PacijentLink findOne(String email);
    
    PacijentLink findPacijentLinkByEmail(String email);

    PacijentLink findByPutanja(String putanja);

    List<PacijentLink> findAll();

    PacijentLink save(PacijentLink pacijentLink);

    void remove(String email);
}
