package com.projekat.UPIB.services;

import java.util.List;


import com.projekat.UPIB.models.ZdravstveniKarton;

public interface IZdravstveniKarton {
	
    public ZdravstveniKarton findOne(Long id);

    public List<ZdravstveniKarton> findAll();

    public ZdravstveniKarton save(ZdravstveniKarton zdravstveniKarton);

    public void remove(Long id);
	
}
