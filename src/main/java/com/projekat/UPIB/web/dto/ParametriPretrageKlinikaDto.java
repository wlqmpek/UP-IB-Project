package com.projekat.UPIB.web.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParametriPretrageKlinikaDto {
    private LocalDate datum;
    private String adresa;
    private int ocena;
}
