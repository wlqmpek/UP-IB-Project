package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.Administrator;
import com.projekat.UPIB.repositories.AdminRepozitorijum;
import com.projekat.UPIB.services.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorSerice implements IAdministratorService {

    @Autowired
    private AdminRepozitorijum adminRepozitorijum;

    //Mozda treba da se zameni sa .findById(id); //WLQMPEK
    //Razlika izmedju dve metode je sto getOne radi lazyLoding dok findById skida ceo Objekat.
    @Override
    public Administrator findOne(Long id) {
        return adminRepozitorijum.getOne(id);
    }

    @Override
    public List<Administrator> findAll() {
        return adminRepozitorijum.findAll();
    }

    @Override
    public Administrator save(Administrator administrator) {
        return adminRepozitorijum.save(administrator);
    }

    @Override
    public void remove(Long id) {
        Administrator administrator = adminRepozitorijum.getOne(id);
        adminRepozitorijum.delete(administrator);
    }
}
