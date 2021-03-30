package com.projekat.UPIB.models;

import com.projekat.UPIB.enums.VrstaAdministratora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
public class Administrator extends Korisnik {

    @Column(name = "klinika", nullable = true, unique = false)
    private Klinika klinika;

    @Enumerated(EnumType.STRING)
    @Column(name = "vrsta_administratora")
    private VrstaAdministratora vrstaAdministratora;
}
