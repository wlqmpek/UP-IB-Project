package com.projekat.UPIB.support.converters.pregled;

import com.projekat.UPIB.models.Pregled;
import com.projekat.UPIB.web.dto.pregled.PregledPretrageFrontDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PregledToPregledPretrageFrontConverter implements Converter<Pregled, PregledPretrageFrontDto> {
    @Override
    public PregledPretrageFrontDto convert(Pregled pregled) {
        PregledPretrageFrontDto pregledPretrage = new PregledPretrageFrontDto();
        pregledPretrage.setIdPregleda(pregled.getIdPregleda());
        pregledPretrage.setCenaPregleda(pregled.getCena());
        pregledPretrage.setPocetakTermina(pregled.getPocetakTermina());
        pregledPretrage.setKrajTermina(pregled.getKrajTermima());
        return pregledPretrage;
    }
}
