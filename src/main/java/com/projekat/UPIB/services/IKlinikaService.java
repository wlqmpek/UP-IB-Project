package com.projekat.UPIB.services;

import java.util.List;
import java.util.Set;

import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.web.dto.ParametriPretrageKlinikaDto;

public interface IKlinikaService {
	
	Klinika findOne(Long id);

    List<Klinika> findAll();

    Klinika save(Klinika klinika);

    List<Klinika> findKlinikasByAdresaContaining(String adresa);

    void remove(Long id);

    Set<Klinika> pretragaKlinika(ParametriPretrageKlinikaDto parametriPretrageKlinikaDto);
}
