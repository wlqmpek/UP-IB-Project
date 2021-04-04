package com.projekat.UPIB.services;

import com.projekat.UPIB.models.Lekar;

import java.util.List;

public interface ILekarService {

    Lekar findOne(Long id);

    List<Lekar> findAll();

    Lekar save(Lekar lekar);

    void remove(Long id);
}
