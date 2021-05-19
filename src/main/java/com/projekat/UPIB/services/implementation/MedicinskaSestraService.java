package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.MedicinskaSestra;
import com.projekat.UPIB.repositories.MedicinskaSestraRepozitorijum;
import com.projekat.UPIB.services.IMedicinskaSestraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicinskaSestraService implements IMedicinskaSestraService {

    @Autowired
    private MedicinskaSestraRepozitorijum medicinskaSestraRepozitorijum;

    @Override
    public MedicinskaSestra findOne(Long id) {
        return medicinskaSestraRepozitorijum.getOne(id);
    }

    @Override
    public List<MedicinskaSestra> findAll() {
        return medicinskaSestraRepozitorijum.findAll();
    }

    @Override
    public MedicinskaSestra save(MedicinskaSestra medicinskaSestra) {
        return medicinskaSestraRepozitorijum.save(medicinskaSestra);
    }

    @Override
    public void remove(Long id) {
        medicinskaSestraRepozitorijum.deleteById(id);
    }
}
