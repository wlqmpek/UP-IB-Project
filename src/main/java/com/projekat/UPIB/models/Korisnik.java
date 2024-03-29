package com.projekat.UPIB.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projekat.UPIB.enums.Roles;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

import com.projekat.UPIB.enums.TipKorisnika;
import com.projekat.UPIB.enums.VrstaAdministratora;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
//END OF LOMBOK
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Korisnik implements UserDetails {
    @Id
    //identity inkrementuje za 1
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_korisnika", unique = true, nullable = false)
    private long idKorisnika;

    @Column(name = "ime_korisnika", unique = false, nullable = false)
    private String imeKorisnika;

    @Column(name = "prezime_korisnika", unique = false, nullable = false)
    private String prezimeKorisnika;

    @Column(name = "email_korisnika", unique = true, nullable = false)
    private String emailKorisnika;

    @Column(name = "lozinka_korisnika", unique = false, nullable = false)
    private String lozinkaKorisnika;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "korisnici_authority",
            joinColumns = @JoinColumn(name = "id_korisnika", referencedColumnName = "id_korisnika"),
            inverseJoinColumns = @JoinColumn(name = "id_authority", referencedColumnName = "id_authority"))
    private List<Authority> authorities;


    public Korisnik(String imeKorisnika, String prezimeKorisnika, String emailKorisnika, String lozinkaKorisnika) {
        this.imeKorisnika = imeKorisnika;
        this.prezimeKorisnika = prezimeKorisnika;
        this.emailKorisnika = emailKorisnika;
        this.lozinkaKorisnika = lozinkaKorisnika;
    }

    @JsonIgnore
    public String getAuthoritiesAsString() {
        StringBuilder sb = new StringBuilder();
        for (Authority authority : getAuthorities()) {
            sb.append(authority.getImeAuthority());
        }
        return sb.toString();
    }

    @Override
    public String getPassword() {
        return getLozinkaKorisnika();
    }

    @Override
    public String getUsername() {
        return getEmailKorisnika();
    }
    
}
