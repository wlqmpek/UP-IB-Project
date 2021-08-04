package com.projekat.UPIB.services.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.models.Pregled;
import com.projekat.UPIB.repositories.KlinikaRepozitorijum;
import com.projekat.UPIB.repositories.PregledRepozitorijum;
import com.projekat.UPIB.services.IPregledService;

@Service
public class PregledService implements IPregledService{

	@Autowired
    private PregledRepozitorijum pregledRepozitorijum;

    @Override
    public Pregled findOne(Long id) {
        return pregledRepozitorijum.getOne(id);
    }

    @Override
    public List<Pregled> findAll() {
        return pregledRepozitorijum.findAll();
    }

    @Override
    public Pregled save(Pregled pregled) {
        return pregledRepozitorijum.save(pregled);
    }

    @Override
    public void remove(Long id) {
    	Pregled pregled = pregledRepozitorijum.getOne(id);
    	pregledRepozitorijum.delete(pregled);
    }

    @Override
    public List<Pregled> findAllByKlinika(Long id) {
        return pregledRepozitorijum.findByKlinikaIdKlinike(id);
    }

    @Override
    public List<Pregled> findByLekarIdKorisnika(Long id) {
        return pregledRepozitorijum.findPregledsByLekarIdKorisnika(id);
    }
}
