package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
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
public class ZdravstveniKarton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zdravstvenog_kartona", nullable = false, unique = true)
    private Long idZdravstvenogKartona;
/*
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "zdravstveniKarton")
    private Pacijent pacijent;*/


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "zdravstveniKarton")
    private Set<Pregled> pregledi = new HashSet<>();
}
