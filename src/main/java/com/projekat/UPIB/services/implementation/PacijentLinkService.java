package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.PacijentLink;
import com.projekat.UPIB.repositories.PacijentLinkRepozitorijum;
import com.projekat.UPIB.services.IPacijentLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacijentLinkService implements IPacijentLinkService {

    @Autowired
    private PacijentLinkRepozitorijum pacijentLinkRepozitorijum;

    @Override
    public PacijentLink findOne(String email) {
        return pacijentLinkRepozitorijum.getOne(email);
    }

    @Override
    public PacijentLink findByPutanja(String putanja) {
        return pacijentLinkRepozitorijum.findByPutanja(putanja);
    }

    @Override
    public List<PacijentLink> findAll() {
        return pacijentLinkRepozitorijum.findAll();
    }

    @Override
    public PacijentLink save(PacijentLink pacijentLink) {
        return pacijentLinkRepozitorijum.save(pacijentLink);
    }

    @Override
    public void remove(String email) {
        pacijentLinkRepozitorijum.deleteById(email);
    }
}
