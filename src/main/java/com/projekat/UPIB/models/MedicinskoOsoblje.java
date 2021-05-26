package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class MedicinskoOsoblje extends Korisnik {

    @ManyToOne
    @JoinColumn(name = "id_klinike", referencedColumnName = "id_klinike", nullable = true)
    private Klinika klinika;

}
