package com.projekat.UPIB.models;

import javax.persistence.*;

@Entity
@Table(name = "ocene_doktora")
public class OceneDoktora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ocene_doktora", nullable = false, unique = true)
    private Long id;

    @OneToOne(cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pacijenta")
    private Pacijent pacijent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lekara")
    private Lekar lekar;

    @Column(name = "ocena", unique = false, nullable = true)
    private int ocena;

    public OceneDoktora() {
    }

    public OceneDoktora(Long id, Pacijent pacijent, Lekar lekar, int ocena) {
        this.id = id;
        this.pacijent = pacijent;
        this.lekar = lekar;
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

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
