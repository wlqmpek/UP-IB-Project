package com.projekat.UPIB.support.converters.lekar;

import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.models.Pregled;
import com.projekat.UPIB.services.implementation.LekarService;
import com.projekat.UPIB.support.converters.pregled.PregledToPregledPretrageFrontConverter;
import com.projekat.UPIB.web.dto.lekar.LekarSaPregledimaFrontDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LekarToLekarSaPregledimaFrontConverter implements Converter<Lekar, LekarSaPregledimaFrontDto> {

    @Autowired
    private PregledToPregledPretrageFrontConverter ptppfc;

    @Autowired
    private LekarService lekarService;

    @Override
    public LekarSaPregledimaFrontDto convert(Lekar lekar) {
        LekarSaPregledimaFrontDto lsp = new LekarSaPregledimaFrontDto();
        lsp.setIdKorisnika(lekar.getIdKorisnika());
        lsp.setImeKorisnika(lekar.getImeKorisnika());
        lsp.setPrezimeKorisnika(lekar.getPrezimeKorisnika());
        lsp.setEmailKorisnika(lekar.getEmailKorisnika());
        lsp.setIdKlinike(lekar.getKlinika().getIdKlinike());
        lsp.setOcena(lekarService.prosecnaOcenaLekara(lekar.getIdKorisnika()));
        return lsp;
    }

    public LekarSaPregledimaFrontDto convert(Lekar lekar, List<Pregled> pregledi) {
        LekarSaPregledimaFrontDto lsp = convert(lekar);
        for(Pregled pregled:pregledi) {
            lsp.getPregledi().add(ptppfc.convert(pregled));
        }
        return lsp;
    }
}
