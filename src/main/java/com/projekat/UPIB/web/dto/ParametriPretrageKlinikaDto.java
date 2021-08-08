package com.projekat.UPIB.web.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParametriPretrageKlinikaDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate datum;
    private String adresa;
    private int ocena;
}
