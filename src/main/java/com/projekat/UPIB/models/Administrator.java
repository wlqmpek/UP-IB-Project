package com.projekat.UPIB.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projekat.UPIB.enums.VrstaAdministratora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
//END OF LOMBOK
@Entity
@Table(name = "administrator")
public class Administrator extends Korisnik {

//	@Column(name = "klinika", nullable = true, unique = false)
    @ManyToOne
    @JoinColumn(name = "id_klinike", referencedColumnName = "id_klinike", nullable = true)
    private Klinika klinika;

    @Enumerated(EnumType.STRING)
    @Column(name = "vrsta_administratora")
    private VrstaAdministratora vrstaAdministratora;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "korisnik_authority",
            joinColumns = @JoinColumn(name = "id_korisnika", referencedColumnName = "id_korisnika"),
            inverseJoinColumns = @JoinColumn(name = "id_authority", referencedColumnName = "id_authority"))
    private List<Authority> authorities;

    // Odavde na dole je sve iz UserDetails - WLQ

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
