package com.projekat.UPIB.models;

import javax.persistence.*;

@Entity
@Table(name = "ocene_klinike")
public class OceneKlinike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ocene_klinike", unique = true, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnika")
    private Pacijent pacijent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_klinike")
    private Klinika klinika;

    @Column(name = "ocena", unique = false, nullable = true)
    private int ocena;

    public OceneKlinike() {
    }

    public OceneKlinike(Long id, Pacijent pacijent, Klinika klinika, int ocena) {
        this.id = id;
        this.pacijent = pacijent;
        this.klinika = klinika;
        this.ocena = ocena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
