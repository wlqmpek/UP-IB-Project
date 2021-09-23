package com.projekat.UPIB.services;

import com.projekat.UPIB.models.ZdravstveniKartonEncrypted;

public interface IZdravstveniKartonEncrypted {

    ZdravstveniKartonEncrypted findAll();

    ZdravstveniKartonEncrypted save(ZdravstveniKartonEncrypted zdravstveniKartonEncrypted);
}
