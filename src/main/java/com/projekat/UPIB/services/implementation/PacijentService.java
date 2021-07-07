package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.repositories.PacijentRepozitorijum;
import com.projekat.UPIB.services.IPacijentService;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacijentService implements IPacijentService {

    @Autowired
    private PacijentRepozitorijum pacijentRepozitorijum;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //Mozda treba da se zameni sa .findById(id); //WLQMPEK
    //Razlika izmedju dve metode je sto getOne radi lazyLoding dok findById skida ceo Objekat.
    @Override
    public Pacijent findOne(Long id) {
        return pacijentRepozitorijum.findById(id).get();
    }

    @Override
    public List<Pacijent> findAll() {
        return pacijentRepozitorijum.findAll();
    }

    //Funkcionise po principu equals.ignoreCase() bas kako bi i trebalo u slucaju emaila!
    @Override
    public Pacijent findPacijentByEmailKorisnika(String emailKorisnika) {
        return pacijentRepozitorijum.findPacijentByEmailKorisnika(emailKorisnika);
    }

    @Override
    public Pacijent save(Pacijent pacijent) {
        System.out.println("Pokusavamo da sacuvamo " +pacijent.getImeKorisnika());
        return pacijentRepozitorijum.save(pacijent);
    }

    @Override
    public void remove(Long id) {
        Pacijent pacijent = pacijentRepozitorijum.getOne(id);
        pacijentRepozitorijum.delete(pacijent);
    }
}
