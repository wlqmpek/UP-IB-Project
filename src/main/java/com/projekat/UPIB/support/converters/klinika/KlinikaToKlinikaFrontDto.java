package com.projekat.UPIB.support.converters.klinika;

import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.services.implementation.OceneKlinikeService;
import com.projekat.UPIB.web.dto.klinika.KlinikaFrontDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class KlinikaToKlinikaFrontDto implements Converter<Klinika, KlinikaFrontDto> {

    @Autowired
    private OceneKlinikeService oceneKlinikeService;

    @Override
    public KlinikaFrontDto convert(Klinika klinika) {
        KlinikaFrontDto klinikaFrontDto = new KlinikaFrontDto();
        klinikaFrontDto.setIdKlinike(klinika.getIdKlinike());
        klinikaFrontDto.setNaziv(klinika.getNaziv());
        klinikaFrontDto.setAdresa(klinika.getAdresa());
        klinikaFrontDto.setOpis(klinika.getOpis());
        klinikaFrontDto.setOcena(oceneKlinikeService.avgOcena(klinika.getIdKlinike()));
        return klinikaFrontDto;
    }
}
