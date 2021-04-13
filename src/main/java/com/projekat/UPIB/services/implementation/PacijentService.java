package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.repositories.PacijentRepozitorijum;
import com.projekat.UPIB.services.IPacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacijentService implements IPacijentService {

    @Autowired
    private PacijentRepozitorijum pacijentRepozitorijum;

    @Override
    public Pacijent findOne(Long id) {
        return pacijentRepozitorijum.findById(id).get();
    }

    @Override
    public List<Pacijent> findAll() {
        return pacijentRepozitorijum.findAll();
    }

    @Override
    public Pacijent save(Pacijent pacijent) {
        return pacijentRepozitorijum.save(pacijent);
    }

    @Override
    public void remove(Long id) {

        Pacijent pacijent = pacijentRepozitorijum.getOne(id);
        pacijentRepozitorijum.delete(pacijent);

    }
}
