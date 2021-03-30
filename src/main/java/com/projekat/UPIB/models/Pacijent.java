package com.projekat.UPIB.models;

import com.projekat.UPIB.enums.StatusKorisnika;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
@Entity
@Table(name = "pacijent")
public class Pacijent extends Korisnik {

    @Column(name = "jbzo", unique = true, nullable = false)
    private String JBZO;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zdravstvenog_kartona", referencedColumnName = "id_zdravstvenog_kartona")
    private ZdravstveniKarton zdravstveniKarton;

    @Column(name = "status_korisnika", unique = true, nullable = false)
    private StatusKorisnika statusKorisnika;
}
