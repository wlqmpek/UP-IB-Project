package com.projekat.UPIB.web.dto.lekar;

import com.projekat.UPIB.web.dto.pregled.PregledFrontendDTO;
import com.projekat.UPIB.web.dto.pregled.PregledPretrageFrontDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class LekarSaPregledimaFrontDto extends LekarFrontendDTO {
    //Ovaj atribut bi trebao da sadrzi samo preglede koji se uklapaju u definisan termin
    private Set<PregledPretrageFrontDto> pregledi = new HashSet<>();
    private Double ocena;
}
