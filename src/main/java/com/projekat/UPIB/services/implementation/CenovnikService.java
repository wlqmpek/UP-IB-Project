package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.Cenovnik;
import com.projekat.UPIB.repositories.CenovnikRepozitorijum;
import com.projekat.UPIB.services.ICenovnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CenovnikService implements ICenovnikService {

    @Autowired
    private CenovnikRepozitorijum cenovnikRepozitorijum;

    @Override
    public List<Cenovnik> getByClinic(Long id) {
        return cenovnikRepozitorijum.findByKlinikaIdKlinike(id);
    }

    @Override
    public Cenovnik getCenovnik(Long id) {
        return cenovnikRepozitorijum.getOne(id);
    }

    @Override
    public Cenovnik save(Cenovnik cenovnik) {
        return cenovnikRepozitorijum.save(cenovnik);
    }

    @Override
    public void remove(Long id) {
        cenovnikRepozitorijum.deleteById(id);
    }
}
