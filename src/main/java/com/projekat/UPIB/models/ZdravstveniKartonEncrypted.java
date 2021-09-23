package com.projekat.UPIB.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zdravstveni_karton_encrypted")
public class ZdravstveniKartonEncrypted {

    @Id
    @Column(name = "encrypted", nullable = false, unique = true)
    private String encrypted;

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
