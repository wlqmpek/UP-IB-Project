package com.projekat.UPIB.services;

import java.util.List;

import com.projekat.UPIB.models.Recept;

public interface IReceptService {

	Recept findOne(Long id);

    List<Recept> findAll();

    Recept save(Recept recept);

    void remove(Long id);
}
