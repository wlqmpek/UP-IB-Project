package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.repositories.LekarRepozitorijum;
import com.projekat.UPIB.services.ILekarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LekarService implements ILekarService {

    @Autowired
    private LekarRepozitorijum lekarRepozitorijum;

    //Mozda treba da se zameni sa .findById(id); //WLQMPEK
    //Razlika izmedju dve metode je sto getOne radi lazyLoding dok findById skida ceo Objekat.
    @Override
    public Lekar findOne(Long id) {
        return lekarRepozitorijum.getOne(id);
    }

    @Override
    public List<Lekar> findAll() {
        return lekarRepozitorijum.findAll();
    }

    @Override
    public Lekar save(Lekar lekar) {
        return lekarRepozitorijum.save(lekar);
    }

    @Override
    public void remove(Long id) {

        Lekar lekar = lekarRepozitorijum.getOne(id);
        lekarRepozitorijum.delete(lekar);
    }

}
