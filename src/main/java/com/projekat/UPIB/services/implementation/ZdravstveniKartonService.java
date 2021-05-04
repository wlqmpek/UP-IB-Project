package com.projekat.UPIB.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projekat.UPIB.models.Recept;
import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.repositories.ReceptRepozitorijum;
import com.projekat.UPIB.repositories.ZdravstveniKartonRepozitorijum;
import com.projekat.UPIB.services.IZdravstveniKarton;

@Service
public class ZdravstveniKartonService implements IZdravstveniKarton{
	@Autowired
    private ZdravstveniKartonRepozitorijum zdravstveniKartonRepozitorijum;

    @Override
    public ZdravstveniKarton findOne(Long id) {
        return zdravstveniKartonRepozitorijum.getOne(id);
    }

    @Override
    public List<ZdravstveniKarton> findAll() {
        return zdravstveniKartonRepozitorijum.findAll();
    }

    @Override
    public ZdravstveniKarton save(ZdravstveniKarton zdravstveniKarton) {
        return zdravstveniKartonRepozitorijum.save(zdravstveniKarton);
    }

    @Override
    public void remove(Long id) {
    	ZdravstveniKarton zdravstveniKarton = zdravstveniKartonRepozitorijum.getOne(id);
    	zdravstveniKartonRepozitorijum.delete(zdravstveniKarton);
    }
}
