package com.projekat.UPIB.models;
import lombok.Data;

import javax.persistence.*;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
//END OF LOMBOK
@Entity
@Table(name = "lekar")
public class Lekar extends MedicinskoOsoblje {


//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lekar")
//    private Set<Pregled> pregledi = new HashSet<>();

    // Odavde pa na dole je sve iz interfejsa UserDetails. - WLQ

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


}
