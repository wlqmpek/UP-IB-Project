package com.projekat.UPIB.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "zdravstveni_karton_encrypted")
public class ZdravstveniKartonEncrypted {

    @Id
    private Long id;

    @Column(name = "encrypted", nullable = false, unique = true)
    @Type(type = "text")
    private String encrypted;

    public ZdravstveniKartonEncrypted(){

    }

    public ZdravstveniKartonEncrypted(Long id, String encrypted){
        this.id = id;
        this.encrypted = encrypted;
    }

    public ZdravstveniKartonEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }
}
