package com.projekat.UPIB.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//END OF LOMBOK
@Entity
@Table(name = "klinika")
public class Klinika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klinike", unique = true, nullable = false)
    private Long idKlinike;

    @Column(name = "naziv", nullable = false, unique = false)
    private String naziv;

    @Column(name = "adresa", nullable = false, unique = false)
    private String adresa;

    @Column(name = "opis", nullable = false, unique = false)
    private String opis;
    
//    @Column(name = "slobodni_termini", nullable = true, unique = false)

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "klinika")
    private Set<Pregled> slobodniTermini = new HashSet<Pregled>();

//    private Double ocena;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "klinika")
    private Set<Administrator> administratori = new HashSet<Administrator>();
}
