package com.projekat.UPIB.services;

import java.util.List;
import java.util.Set;

import com.projekat.UPIB.models.Pregled;

public interface IPregledService {
	Pregled findOne(Long id);

    List<Pregled> findAll();

    Pregled save(Pregled pregled);

    void remove(Long id);

    List<Pregled> findAllByKlinika(Long id);

    List<Pregled> findByLekarIdKorisnika (Long id);
}
