package com.projekat.UPIB.models;

import com.projekat.UPIB.web.dto.LekarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
@Entity
@Table(name = "lekar")
public class Lekar extends MedicinskoOsoblje {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lekar")
    private Set<Pregled> pregledi = new HashSet<>();

    public Lekar(LekarDTO lekarDTO){
        this.setIdKorisnika(lekarDTO.getIdKorisnika());
        this.setImeKorisnika(lekarDTO.getImeKorisnika());
        this.setPrezimeKorisnika(lekarDTO.getPrezimeKorisnika());
        this.setEmailKorisnika(lekarDTO.getEmailKorisnika());
        this.setLozinkaKorisnika(lekarDTO.getLozinkaKorisnika());
    }

}
