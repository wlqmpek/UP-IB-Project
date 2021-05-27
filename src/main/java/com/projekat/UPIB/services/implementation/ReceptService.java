package com.projekat.UPIB.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.UPIB.models.Recept;
import com.projekat.UPIB.repositories.ReceptRepozitorijum;
import com.projekat.UPIB.services.IReceptService;

@Service
public class ReceptService implements IReceptService{

	@Autowired
    private ReceptRepozitorijum receptRepozitorijum;

    @Override
    public Recept findOne(Long id) {
        return receptRepozitorijum.getOne(id);
    }

    @Override
    public List<Recept> findAll() {
        return receptRepozitorijum.findAll();
    }

    @Override
    public Recept save(Recept recept) {
        return receptRepozitorijum.save(recept);
    }

    @Override
    public void remove(Long id) {
    	Recept recept = receptRepozitorijum.getOne(id);
    	receptRepozitorijum.delete(recept);
    }
}
