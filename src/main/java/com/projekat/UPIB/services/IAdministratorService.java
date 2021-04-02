package com.projekat.UPIB.services;


import com.projekat.UPIB.models.Administrator;

import java.util.List;

public interface IAdministratorService {

    Administrator findOne(Long id);

    List<Administrator> findAll();

    Administrator save(Administrator administrator);

    void remove(Long id);
}
