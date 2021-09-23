package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.models.Pregled;
import com.projekat.UPIB.repositories.PacijentRepozitorijum;
import com.projekat.UPIB.security.EnkripcijaDekripcijaUtils;
import com.projekat.UPIB.services.IPacijentService;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PacijentService implements IPacijentService {

    @Autowired
    private PacijentRepozitorijum pacijentRepozitorijum;

    @Autowired
    private EnkripcijaDekripcijaUtils enkripcijaDekripcijaUtils;

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
        Optional<Pacijent> pacijent = pacijentRepozitorijum.findPacijentByEmailKorisnika(emailKorisnika);
        if(pacijent.isEmpty()) {
            throw new NoSuchElementException("Pacijent with email = " + emailKorisnika + " not found!");
        }
        return pacijent.get();
    }

    @Override
    public Pacijent save(Pacijent pacijent) {
        //pacijent.setJBZO(enkripcijaDekripcijaUtils.enkriptujJBZO(pacijent.getJBZO(), pacijent.getEmailKorisnika()));
        return pacijentRepozitorijum.save(pacijent);
    }

    @Override
    public Pacijent update(Pacijent pacijent) {
        return pacijentRepozitorijum.save(pacijent);
    }

    @Override
    public void remove(Long id) {
        Pacijent pacijent = pacijentRepozitorijum.getOne(id);
        pacijentRepozitorijum.delete(pacijent);
    }
}
