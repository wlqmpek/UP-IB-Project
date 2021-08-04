package com.projekat.UPIB.support.converters.pacijent;

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
            pacijentFrontDTO.setIme(pacijent.getImeKorisnika());
            pacijentFrontDTO.setPrezime(pacijent.getPrezimeKorisnika());
            pacijentFrontDTO.setId(pacijent.getIdKorisnika());
        }
        return pacijentFrontDTO;
    }
}
