package com.projekat.UPIB.models;

import com.projekat.UPIB.enums.VrstaAdministratora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
@Entity
@Table(name = "administrator")
public class Administrator extends Korisnik {

//    @Column(name = "klinika", nullable = true, unique = false)
    @ManyToOne
    @JoinColumn(name = "id_klinike", referencedColumnName = "id_klinike", nullable = false)
    private Klinika klinika;

    @Enumerated(EnumType.STRING)
    @Column(name = "vrsta_administratora")
    private VrstaAdministratora vrstaAdministratora;
}
