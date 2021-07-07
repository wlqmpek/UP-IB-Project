package com.projekat.UPIB.support.converters;

import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.services.implementation.AuthorityService;
import com.projekat.UPIB.web.dto.pacijent.PacijentRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PacijentRegisterDtoToPacijent implements Converter<PacijentRegisterDTO, Pacijent> {

    @Autowired
    private AuthorityService authorityService;

    @Override
    public Pacijent convert(PacijentRegisterDTO pacijentRegisterDTO) {
        ZdravstveniKarton zdravstveniKarton = new ZdravstveniKarton();
        Pacijent pacijent = new Pacijent();
        if(pacijentRegisterDTO.getLozinka().equals(pacijentRegisterDTO.getPonovljenaLozinka())) {
            pacijent.setImeKorisnika(pacijentRegisterDTO.getIme());
            pacijent.setPrezimeKorisnika(pacijentRegisterDTO.getPrezime());
            pacijent.setEmailKorisnika(pacijentRegisterDTO.getEmail());
            pacijent.setLozinkaKorisnika(pacijentRegisterDTO.getLozinka());
            pacijent.setZdravstveniKarton(zdravstveniKarton);
            pacijent.setStatusKorisnika(StatusKorisnika.NA_CEKANJU);
            pacijent.setJBZO(pacijentRegisterDTO.getJBZO());
            System.out.println("Authority " + authorityService.findByIdAuthority(pacijentRegisterDTO.getAuthorities()));
            pacijent.setAuthorities(authorityService.findByIdAuthority(pacijentRegisterDTO.getAuthorities()));
            zdravstveniKarton.setPacijent(pacijent);
        } else {
            System.out.println("Password se ne poklapa");
        }

        if(pacijent == null && zdravstveniKarton == null) {
            System.out.println("Konvertor nije u redu");
        } else {
            System.out.println("Konverter je u redu");
        }

        return pacijent;
    }
}
