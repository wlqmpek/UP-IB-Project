package com.projekat.UPIB.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
@Entity
@Table(name = "zdravstveni_karton")
@Builder
public class ZdravstveniKarton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zdravstvenog_kartona", nullable = false, unique = true)
    private Long idZdravstvenogKartona;

    @Column(name = "visina", nullable = true, unique = false)
    private int visina;

    @Column(name = "tezina", nullable = true, unique = false)
    private int tezina;
    
    @Column(name = "krvna_grupa", nullable = true, unique = false)
    private String krvnaGrupa;

    @Column(name = "dioptrija", nullable = true, unique = false)
    private double dioptrija;
    
    @Column(name = "alergije", nullable = true, unique = false)
    private String alergije;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "zdravstveniKarton")
    private Pacijent pacijent;

}
