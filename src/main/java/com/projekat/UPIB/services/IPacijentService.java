package com.projekat.UPIB.services;

import com.projekat.UPIB.models.Pacijent;

import java.util.List;

public interface IPacijentService {

    Pacijent findOne(Long id);

    List<Pacijent> findAll();

    Pacijent findPacijentByEmailKorisnika(String emailKorisnika);

    Pacijent save(Pacijent pacijent);

    void remove(Long id);
}
