package com.projekat.UPIB.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projekat.UPIB.enums.StatusKorisnika;
import lombok.*;
import javax.persistence.*;
import java.util.List;

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
    @JsonIgnore
    /* Ova anotacija ignorise ovaj field kod kreiranja toString metode od strane lomboka.
     zasto? Zato sto je bilo jedenja govana i nije radilo <3 Mrs. - WLQ */
    @ToString.Exclude
    private ZdravstveniKarton zdravstveniKarton;

    @Column(name = "status_korisnika", nullable = false)
    private StatusKorisnika statusKorisnika;

    // Odavde pa na dole je sve iz interfejsa UserDetails.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Pacijent{" +
                "JBZO='" + JBZO + '\'' +
                ", statusKorisnika=" + statusKorisnika +
                "} " + super.toString();
    }
}