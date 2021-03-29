package com.projekat.UPIB.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;

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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "zdravstveniKarton")
    private Pacijent pacijent;


//    private HashSet<Pregled> pregledi;
}
