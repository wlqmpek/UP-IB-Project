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
public abstract class Korisnik {
    @Id
    //identity inkrementuje za 1
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_korisnika", unique = true, nullable = false)
    private long idKorisnika;

    @Column(name = "ime_korisnika", unique = false, nullable = false)
    private String imeKorisnika;

    @Column(name = "prezime_korisnika", unique = false, nullable = false)
    private String prezimeKorisnika;

    @Column(name = "email_korisnika", unique = true, nullable = false)
    private String emailKorisnika;

    @Column(name = "lozinka_korisnika", unique = false, nullable = false)
    private String lozinkaKorisnika;
}
