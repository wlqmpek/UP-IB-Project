package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
@Entity
@Table(name = "pregled")
public class Pregled {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pregleda", nullable = false, unique = true)
    private Long idPregleda;

    @Column(name = "dijagnoza", nullable = true, unique = false)
    private String dijagnoza;

    @Column(name = "opis", nullable = true, unique = false)
    private String opis;

    @ManyToOne
    @JoinColumn(name = "id_lekara", referencedColumnName = "id_korisnika", nullable = true)
    private Lekar lekar;

    @ManyToOne
    @JoinColumn(name = "id_medicinske_sestre", referencedColumnName = "id_korisnika", nullable = true)
    private MedicinskaSestra medicinskaSestra;

    @Column(name = "pocetak_termina", nullable = true, unique = false)
    private LocalDateTime pocetakTermina;
    

    @Column(name = "kraj_termina", nullable = true, unique = false)
    private LocalDateTime krajTermima;

    @Column(name = "cena", nullable = true, unique = false)
    private double cena;

    @Column(name = "popust", nullable = true, unique = false)
    private int popust;

    @ManyToOne
    @JoinColumn(name = "id_zdravstvenog_kartona", referencedColumnName = "id_zdravstvenog_kartona", nullable = false)
    private ZdravstveniKarton zdravstveniKarton;
    
    @ManyToOne
    @JoinColumn(name = "id_klinike", referencedColumnName = "id_klinike", nullable = true)
    @JsonIgnore
    private Klinika klinika;

}
