package com.projekat.UPIB.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.repositories.KlinikaRepozitorijum;
import com.projekat.UPIB.services.IKlinikaService;


//TODO: Pomocu Criteria API ili QueryDLS implementirati search. (docs.spring.io) WLQMPEK
@Service
public class KlinikaService implements IKlinikaService{

	@Autowired
    private KlinikaRepozitorijum klinikaRepozitorijum;

    //Mozda treba da se zameni sa .findById(id); //WLQMPEK
    //Razlika izmedju dve metode je sto getOne radi lazyLoding dok findById skida ceo Objekat.
    @Override
    public Klinika findOne(Long id) {
        return klinikaRepozitorijum.getOne(id);
    }

    @Override
    public List<Klinika> findAll() {
        return klinikaRepozitorijum.findAll();
    }

    @Override
    public Klinika save(Klinika klinika) {
        return klinikaRepozitorijum.save(klinika);
    }

    @Override
    public void remove(Long id) {
    	Klinika klinika = klinikaRepozitorijum.getOne(id);
    	klinikaRepozitorijum.delete(klinika);
    }
	
}
