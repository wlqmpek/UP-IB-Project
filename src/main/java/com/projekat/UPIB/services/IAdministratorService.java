package com.projekat.UPIB.services;


import com.projekat.UPIB.models.Administrator;

import java.util.List;

public interface IAdministratorService {

    Administrator findOne(Long id);

    Administrator findAdministratorByEmailKorisnika(String email);

    List<Administrator> findAll();

    Administrator save(Administrator administrator);

    void remove(Long id);
}
