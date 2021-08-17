package com.projekat.UPIB.support.converters.pacijent;

import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.security.EnkripcijaDekripcijaUtils;
import com.projekat.UPIB.web.dto.pacijent.PacijentFrontDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PacijentToPacijentFrontDto implements Converter<Pacijent, PacijentFrontDTO> {

    @Autowired
    private EnkripcijaDekripcijaUtils enkripcijaDekripcijaUtils;

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
            pacijentFrontDTO.setEmail(pacijent.getEmailKorisnika());
            pacijentFrontDTO.setIdZdravstvenogKartona(pacijent.getZdravstveniKarton().getIdZdravstvenogKartona());
            pacijentFrontDTO.setJBZO(enkripcijaDekripcijaUtils.dekriptujJBZO(pacijent.getJBZO(), pacijent.getEmailKorisnika()));
            pacijentFrontDTO.setStatusKorisnika(pacijent.getStatusKorisnika());
        }
        return pacijentFrontDTO;
    }
}
