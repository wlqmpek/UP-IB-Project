package com.projekat.UPIB.support.converters;

import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.web.dto.pacijent.PacijentFrontDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PacijentToPacijentFrontDto implements Converter<Pacijent, PacijentFrontDTO> {

    @Override
    public PacijentFrontDTO convert(Pacijent pacijent) {
        PacijentFrontDTO pacijentFrontDTO = null;
        if(pacijent == null) {
            //TODO: Baci exception
        } else {
            pacijentFrontDTO = new PacijentFrontDTO();
            pacijentFrontDTO.setImeKorisnika(pacijent.getImeKorisnika());
            pacijentFrontDTO.setPrezimeKorisnika(pacijent.getPrezimeKorisnika());
            pacijentFrontDTO.setIdKorisnika(pacijent.getIdKorisnika());
        }
        return pacijentFrontDTO;
    }
}
