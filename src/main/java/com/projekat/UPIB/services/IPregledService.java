package com.projekat.UPIB.services;

import java.time.LocalDate;
import java.util.List;

import com.projekat.UPIB.models.Pregled;

public interface IPregledService {
	Pregled findOne(Long id);

    List<Pregled> findAll();

    Pregled save(Pregled pregled);

    void remove(Long id);

    List<Pregled> findAllByKlinika(Long id);

    List<Pregled> findByLekarIdKorisnika (Long id);

    public List<Pregled> findPregledsByPocetakTerminaIsOrPocetakTerminaIsAfterAndKlinikaIdKlinike(Long idKlinike, LocalDate datum);

    void zakaziPregled(Long idKorisnika, Long idPregleda);

    Pregled potvrdiPregled(Long idKorisnika, Long idPregleda);
}
