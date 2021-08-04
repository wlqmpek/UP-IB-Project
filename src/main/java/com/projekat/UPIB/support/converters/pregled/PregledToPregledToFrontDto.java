package com.projekat.UPIB.support.converters.pregled;

import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.models.Pregled;
import com.projekat.UPIB.web.dto.pacijent.PacijentFrontDTO;
import com.projekat.UPIB.web.dto.pregled.PregledFrontendDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PregledToPregledToFrontDto implements Converter<Pregled, PregledFrontendDTO> {
    @Override
    public PregledFrontendDTO convert(Pregled pregled) {
        PregledFrontendDTO pregledFrontendDTO = new PregledFrontendDTO();
        pregledFrontendDTO.setIdPregleda(pregled.getIdPregleda());
        pregledFrontendDTO.setDijagnoza(pregled.getDijagnoza());
        pregledFrontendDTO.setOpis(pregled.getOpis());
        pregledFrontendDTO.setIdRecepata(null);
        pregledFrontendDTO.setIdLekara(pregled.getLekar().getIdKorisnika());
        pregledFrontendDTO.setIdMedicinskeSestre(pregled.getMedicinskaSestra().getIdKorisnika());
        pregledFrontendDTO.setPocetakTermina(pregled.getPocetakTermina());
        pregledFrontendDTO.setKrajTermina(pregled.getKrajTermima());
        pregledFrontendDTO.setCena(pregled.getCena());
        pregledFrontendDTO.setPopust(pregled.getPopust());
        pregledFrontendDTO.setIdZdravstvenogKartona(pregled.getZdravstveniKarton().getIdZdravstvenogKartona());
        pregledFrontendDTO.setIdKlinike(pregled.getKlinika().getIdKlinike());
        return pregledFrontendDTO;
    }
}
