package com.projekat.UPIB.web.dto.pregled;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParametriPretragePregledaDto {
    private Long idKlinike;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate odDatuma;
}
