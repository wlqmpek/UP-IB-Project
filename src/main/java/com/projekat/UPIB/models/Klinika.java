package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
@Entity
@Table(name = "klinika")
public class Klinika {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_klinike", unique = true, nullable = false)
    private Long idKlinike;

    @Column(name = "naziv", nullable = false, unique = false)
    private String naziv;

    @Column(name = "adresa", nullable = false, unique = false)
    private String adresa;

    @Column(name = "opis", nullable = false, unique = false)
    private String opis;

    @Column(name = "slobodni_termini", nullable = false, unique = false)
    private LocalDateTime slobodniTermini;

//    private Double ocena;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "klinika")
    private Set<Administrator> administratori = new HashSet<Administrator>();
}
