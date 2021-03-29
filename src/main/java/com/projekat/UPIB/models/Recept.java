package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
public class Recept {
    private Long idRecepta;
    private HashSet<String> lekovi;
    private Pregled pregled;
    private boolean overen = false;
}
