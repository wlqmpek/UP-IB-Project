package com.projekat.UPIB.services;

import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.web.dto.lekar.LekarSaPregledimaFrontDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ILekarService {

    Lekar findOne(Long id);

    Lekar findLekarByEmailKorisnika(String email);

    List<Lekar> findAll();

    Lekar save(Lekar lekar);

    void remove(Long id);

    List<Lekar> findLekarByKlinika(Long id);

    Set<LekarSaPregledimaFrontDto> pretragaLekaraPoSlobodnomTerminu(LocalDate date);
}
