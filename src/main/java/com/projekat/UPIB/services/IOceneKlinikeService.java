package com.projekat.UPIB.services;

import com.projekat.UPIB.models.OceneKlinike;

import java.util.List;

public interface IOceneKlinikeService {

    OceneKlinike findOne(Long id);

    List<OceneKlinike> findAll();

    OceneKlinike save(OceneKlinike oceneKlinike);

    void remove(Long id);

    Double avgOcena(Long id);
}
