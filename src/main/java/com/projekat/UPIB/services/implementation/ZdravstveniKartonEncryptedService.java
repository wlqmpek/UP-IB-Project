package com.projekat.UPIB.services.implementation;

import com.projekat.UPIB.models.ZdravstveniKartonEncrypted;
import com.projekat.UPIB.repositories.ZdravstveniKartonEncryptedRepozitorijum;
import com.projekat.UPIB.services.IZdravstveniKartonEncrypted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZdravstveniKartonEncryptedService implements IZdravstveniKartonEncrypted {

    @Autowired
    private ZdravstveniKartonEncryptedRepozitorijum repozitorijum;

    @Override
    public ZdravstveniKartonEncrypted findAll() {
        return repozitorijum.findAll().get(0);
    }

    @Override
    public ZdravstveniKartonEncrypted save(ZdravstveniKartonEncrypted zdravstveniKartonEncrypted) {
        return repozitorijum.save(zdravstveniKartonEncrypted);
    }
}
