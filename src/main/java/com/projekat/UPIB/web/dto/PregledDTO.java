package com.projekat.UPIB.web.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PregledDTO implements Serializable{

    private Long idPregleda;
    private String dijagnoza;
    private String opis;
    private ReceptDTO recept;
    private LekarDTO lekar;
    private MedicinskaSestraDTO medicinskaSestra;
    private LocalDateTime pocetakTermina;
    private LocalDateTime krajTermima;
    private double cena;
    private int popust;
    private ZdravstveniKartonDTO zdravstveniKarton;
    private KlinikaDTO klinika;
    
    
	public PregledDTO() {
		super();
	}


	public PregledDTO(Long idPregleda, String dijagnoza, String opis, ReceptDTO recept, LekarDTO lekar,
			MedicinskaSestraDTO medicinskaSestra, LocalDateTime pocetakTermina, LocalDateTime krajTermima, double cena,
			int popust, ZdravstveniKartonDTO zdravstveniKarton, KlinikaDTO klinika) {
		super();
		this.idPregleda = idPregleda;
		this.dijagnoza = dijagnoza;
		this.opis = opis;
		this.recept = recept;
		this.lekar = lekar;
		this.medicinskaSestra = medicinskaSestra;
		this.pocetakTermina = pocetakTermina;
		this.krajTermima = krajTermima;
		this.cena = cena;
		this.popust = popust;
		this.zdravstveniKarton = zdravstveniKarton;
		this.klinika = klinika;
	}


	
	public Long getIdPregleda() {
		return idPregleda;
	}
	public void setIdPregleda(Long idPregleda) {
		this.idPregleda = idPregleda;
	}
	public String getDijagnoza() {
		return dijagnoza;
	}
	public void setDijagnoza(String dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public ReceptDTO getRecept() {
		return recept;
	}
	public void setRecept(ReceptDTO recept) {
		this.recept = recept;
	}
	public LekarDTO getLekar() {
		return lekar;
	}
	public void setLekar(LekarDTO lekar) {
		this.lekar = lekar;
	}
	public MedicinskaSestraDTO getMedicinskaSestra() {
		return medicinskaSestra;
	}
	public void setMedicinskaSestra(MedicinskaSestraDTO medicinskaSestra) {
		this.medicinskaSestra = medicinskaSestra;
	}
	public LocalDateTime getPocetakTermina() {
		return pocetakTermina;
	}
	public void setPocetakTermina(LocalDateTime pocetakTermina) {
		this.pocetakTermina = pocetakTermina;
	}
	public LocalDateTime getKrajTermima() {
		return krajTermima;
	}
	public void setKrajTermima(LocalDateTime krajTermima) {
		this.krajTermima = krajTermima;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public int getPopust() {
		return popust;
	}
	public void setPopust(int popust) {
		this.popust = popust;
	}
	public ZdravstveniKartonDTO getZdravstveniKarton() {
		return zdravstveniKarton;
	}
	public void setZdravstveniKarton(ZdravstveniKartonDTO zdravstveniKarton) {
		this.zdravstveniKarton = zdravstveniKarton;
	}
	public KlinikaDTO getKlinika() {
		return klinika;
	}
	public void setKlinika(KlinikaDTO klinika) {
		this.klinika = klinika;
	}
    

}
