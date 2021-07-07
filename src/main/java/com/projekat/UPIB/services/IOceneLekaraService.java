package com.projekat.UPIB.services;

import com.projekat.UPIB.models.OceneDoktora;

import java.util.List;

public interface IOceneLekaraService {

    OceneDoktora findOne(Long id);

    List<OceneDoktora> findAll();

    OceneDoktora save(OceneDoktora oceneDoktora);

    void remove(Long id);

    Double avgOcena(Long id);
}
