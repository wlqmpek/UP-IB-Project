package com.projekat.UPIB.services;

import java.util.List;

import com.projekat.UPIB.models.Klinika;

public interface IKlinikaService {
	
	Klinika findOne(Long id);

    List<Klinika> findAll();

    Klinika save(Klinika klinika);

    void remove(Long id);
	
}
