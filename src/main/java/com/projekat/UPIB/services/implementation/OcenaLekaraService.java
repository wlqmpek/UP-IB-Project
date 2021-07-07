package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.OceneDoktora;
import com.projekat.UPIB.repositories.OceneLekaraRepozitorijum;
import com.projekat.UPIB.services.IOceneLekaraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcenaLekaraService implements IOceneLekaraService {

    @Autowired
    private OceneLekaraRepozitorijum oceneLekaraRepozitorijum;

    @Override
    public OceneDoktora findOne(Long id) {
        return oceneLekaraRepozitorijum.getOne(id);
    }

    @Override
    public List<OceneDoktora> findAll() {
        return oceneLekaraRepozitorijum.findAll();
    }

    @Override
    public OceneDoktora save(OceneDoktora oceneDoktora) {
        return oceneLekaraRepozitorijum.save(oceneDoktora);
    }

    @Override
    public void remove(Long id) {
        oceneLekaraRepozitorijum.deleteById(id);
    }

    @Override
    public Double avgOcena(Long id) {
        return oceneLekaraRepozitorijum.avgOcena(id);
    }
}
