package com.projekat.UPIB.models;

import com.projekat.UPIB.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
@Entity
@Table(name = "medicinska_sestra")
public class MedicinskaSestra extends MedicinskoOsoblje {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medicinskaSestra")
    private Set<Pregled> pregledi = new HashSet<>();

    // Odavde pa na dole je sve za potrebe UserDetails Interfejsa. - WLQ

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
