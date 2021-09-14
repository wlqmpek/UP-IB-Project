package com.projekat.UPIB.support.converters.zdravstveniKarton;

import com.projekat.UPIB.models.ZdravstveniKarton;
import com.projekat.UPIB.web.dto.zdravstveniKarton.ZdravstveniKartonFrontendDTO;
import org.springframework.core.convert.converter.Converter;

public class ZdravstveniKartonToZdravstveniKartonFrontDto implements Converter<ZdravstveniKarton, ZdravstveniKartonFrontendDTO> {
    @Override
    public ZdravstveniKartonFrontendDTO convert(ZdravstveniKarton zdravstveniKarton) {
        ZdravstveniKartonFrontendDTO zdravstveniKartonFrontendDTO = new ZdravstveniKartonFrontendDTO();
        zdravstveniKartonFrontendDTO.setIdZdravstvenogKartona(zdravstveniKarton.getIdZdravstvenogKartona());
        zdravstveniKartonFrontendDTO.setVisina(zdravstveniKarton.getVisina());
        zdravstveniKartonFrontendDTO.setTezina(zdravstveniKarton.getTezina());
        zdravstveniKartonFrontendDTO.setKrvnaGrupa(zdravstveniKarton.getKrvnaGrupa());
        zdravstveniKartonFrontendDTO.setDioptrija(zdravstveniKarton.getDioptrija());
        zdravstveniKartonFrontendDTO.setAlergije(zdravstveniKarton.getAlergije());
        return zdravstveniKartonFrontendDTO;
    }
}
