package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
public class Pregled {

    private Long idPregleda;
    private String dijagnoza;
    private String opis;
    private Recept recept;
    private Lekar lekar;
    private MedicinskaSestra medicinskaSestra;
    private LocalDateTime pocetakTermina;
    private LocalDateTime krajTermima;
    private double cena;
    private int popust;
    private ZdravstveniKarton zdravstveniKarton;

}
