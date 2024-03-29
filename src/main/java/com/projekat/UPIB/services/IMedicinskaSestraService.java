package com.projekat.UPIB.services;

import com.projekat.UPIB.models.MedicinskaSestra;

import java.util.List;

public interface IMedicinskaSestraService {

    MedicinskaSestra findOne(Long id);
    
    MedicinskaSestra findMedicinskaSestraByEmailKorisnika(String email);

    List<MedicinskaSestra> findAll();

    MedicinskaSestra save(MedicinskaSestra medicinskaSestra);

    void remove(Long id);

    List<MedicinskaSestra> getAllForList(Long id);
}
