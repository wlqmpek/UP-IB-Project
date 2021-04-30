package com.projekat.UPIB.services;

import java.util.List;

import com.projekat.UPIB.models.Pregled;

public interface IPregledService {
	Pregled findOne(Long id);

    List<Pregled> findAll();

    Pregled save(Pregled pregled);

    void remove(Long id);
}
