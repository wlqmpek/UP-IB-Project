package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
public class Klinika {
    private Long idKlinike;
    private String naziv;
    private String adresa;
    private String opis;
    private LocalDateTime slobodniTermini;
    private Double ocena;
//    private HashSet<Administrator> administratori;
}
