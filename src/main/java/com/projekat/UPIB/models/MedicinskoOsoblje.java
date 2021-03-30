package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK

public abstract class MedicinskoOsoblje extends Korisnik {

    private Klinika klinika;

}
