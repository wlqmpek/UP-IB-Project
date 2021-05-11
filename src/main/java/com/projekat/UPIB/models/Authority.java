package com.projekat.UPIB.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

// POJO koji implementira Spring Security GrantedAuthority kojim se mogu definisati role u aplikaciji
@Entity
@Table(name="authority")
public class Authority implements GrantedAuthority {

    @Id
    @Column(name="id_authority")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuthority;

    @Column(name="ime_authority")
    private String imeAuthority;

    @Override
    public String getAuthority() {
        return imeAuthority;
    }

    public Long getIdAuthority() {
        return idAuthority;
    }

    public void setIdAuthority(Long idAuthority) {
        this.idAuthority = idAuthority;
    }

    public String getImeAuthority() {
        return imeAuthority;
    }

    public void setImeAuthority(String imeAuthority) {
        this.imeAuthority = imeAuthority;
    }
}
