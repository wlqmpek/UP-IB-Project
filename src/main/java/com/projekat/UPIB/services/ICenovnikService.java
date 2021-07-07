package com.projekat.UPIB.services;

import com.projekat.UPIB.models.Cenovnik;

import java.util.List;

public interface ICenovnikService {

    List<Cenovnik> getByClinic(Long id);

    Cenovnik getCenovnik(Long id);

    Cenovnik save(Cenovnik cenovnik);

    void remove(Long id);
}
