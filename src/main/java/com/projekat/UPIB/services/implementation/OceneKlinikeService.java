package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.OceneKlinike;
import com.projekat.UPIB.repositories.OceneKlinikeRepozitorijum;
import com.projekat.UPIB.services.IOceneKlinikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OceneKlinikeService implements IOceneKlinikeService {

    @Autowired
    private OceneKlinikeRepozitorijum oceneKlinikeRepozitorijum;

    @Override
    public OceneKlinike findOne(Long id) {
        return oceneKlinikeRepozitorijum.getOne(id);
    }

    @Override
    public List<OceneKlinike> findAll() {
        return oceneKlinikeRepozitorijum.findAll();
    }

    @Override
    public OceneKlinike save(OceneKlinike oceneKlinike) {
        return oceneKlinikeRepozitorijum.save(oceneKlinike);
    }

    @Override
    public void remove(Long id) {
        oceneKlinikeRepozitorijum.deleteById(id);
    }

    @Override
    public Double avgOcena(Long id) {
        return oceneKlinikeRepozitorijum.avgOcena(id);
    }
}
