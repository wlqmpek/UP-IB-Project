package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
@Entity
@Table(name = "recept")
public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_recepta", nullable = false, unique = true)
    private Long idRecepta;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<String> lekovi = new ArrayList<>();
    @Column(name = "opis_recepta", unique = false, nullable = false)
    private String opisRecepta;
    
    @ManyToOne
    @JoinColumn(name = "id_pregleda", referencedColumnName = "id_pregleda", nullable = true, insertable= true, updatable=true)
    private Pregled pregled;
    
    @Column(name = "overen", nullable = false, unique = false)
    private boolean overen = false;
}
