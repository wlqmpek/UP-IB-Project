package com.projekat.UPIB.models;

import javax.persistence.*;

@Entity
@Table(name = "cenovnik")
public class Cenovnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cenovnika")
    private Long id;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @Column(name = "cena", nullable = false)
    private Double cena;

    @ManyToOne
    @JoinColumn(name = "id_klinike", referencedColumnName = "id_klinike", nullable = false)
    private Klinika klinika;

    public Cenovnik() {
    }

    public Cenovnik(Long id, String naziv, Double cena, Klinika klinika) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.klinika = klinika;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }
}
