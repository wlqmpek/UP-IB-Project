package com.projekat.UPIB.support.converters.pacijent;

import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.services.IPacijentService;
import com.projekat.UPIB.web.dto.pacijent.PacijentEditDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PacijentEditDtoToPacijent {

    @Autowired
    private IPacijentService pacijentService;

    public Pacijent convert(Pacijent pacijentOld, PacijentEditDto pacijentEditDto) {
        if(pacijentOld == null) {
            //TODO: Ovde baci custom exception
            System.out.println("Nije nadjen pacijent u konvertoru");
        } else {
            if(pacijentEditDto.getLozinka().equals(pacijentEditDto.getPonovljenaLozinka())) {
                pacijentOld.setImeKorisnika(pacijentEditDto.getIme());
                pacijentOld.setPrezimeKorisnika(pacijentEditDto.getPrezime());
                pacijentOld.setLozinkaKorisnika(pacijentEditDto.getLozinka());
            } else {
                //TODO: Kreiraj custom exception i baci ga ovde, hendlaj ga u ControllerAdvice. - WLQ
                System.out.println("Lozinke se ne poklapaju");
            }
        }
        return pacijentOld;
    }
}
