package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recepta", nullable = false, unique = true)
    private Long idRecepta;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<String> lekovi = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "recept")
    private Pregled pregled;

    @Column(name = "overen", nullable = false, unique = false)
    private boolean overen = false;
}
