package com.projekat.UPIB.web.dto.pregled;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PregledPretrageFrontDto {
    private Long idPregleda;
    private double cenaPregleda;
    private LocalDateTime pocetakTermina;
    private LocalDateTime krajTermina;
}
