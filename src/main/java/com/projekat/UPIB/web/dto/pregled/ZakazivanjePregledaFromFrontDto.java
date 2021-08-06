package com.projekat.UPIB.web.dto.pregled;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ZakazivanjePregledaFromFrontDto {
    private Long idKorisnika;
    private Long idPregleda;
}
