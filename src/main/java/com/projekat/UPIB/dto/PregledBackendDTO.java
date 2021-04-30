package com.projekat.UPIB.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PregledBackendDTO implements Serializable{

    private Long idPregleda;
    private String dijagnoza;
    private String opis;
    private Long idRecepta;
    private Long idLekara;
    private Long idMedicinskeSestre;
    private LocalDateTime pocetakTermina;
    private LocalDateTime krajTermima;
    private double cena;
    private int popust;
    private Long idZdravstvenogKartona;
    private Long idKlinike;
    
    
	public PregledBackendDTO() {
		super();
	}


	public PregledBackendDTO(Long idPregleda, String dijagnoza, String opis, Long idRecepta, Long idLekara,
			Long idMedicinskeSestre, LocalDateTime pocetakTermina, LocalDateTime krajTermima, double cena, int popust,
			Long idZdravstvenogKartona, Long idKlinike) {
		super();
		this.idPregleda = idPregleda;
		this.dijagnoza = dijagnoza;
		this.opis = opis;
		this.idRecepta = idRecepta;
		this.idLekara = idLekara;
		this.idMedicinskeSestre = idMedicinskeSestre;
		this.pocetakTermina = pocetakTermina;
		this.krajTermima = krajTermima;
		this.cena = cena;
		this.popust = popust;
		this.idZdravstvenogKartona = idZdravstvenogKartona;
		this.idKlinike = idKlinike;
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


	public Long getIdRecepta() {
		return idRecepta;
	}


	public void setIdRecepta(Long idRecepta) {
		this.idRecepta = idRecepta;
	}


	public Long getIdLekara() {
		return idLekara;
	}


	public void setIdLekara(Long idLekara) {
		this.idLekara = idLekara;
	}


	public Long getIdMedicinskeSestre() {
		return idMedicinskeSestre;
	}


	public void setIdMedicinskeSestre(Long idMedicinskeSestre) {
		this.idMedicinskeSestre = idMedicinskeSestre;
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


	public Long getIdZdravstvenogKartona() {
		return idZdravstvenogKartona;
	}


	public void setIdZdravstvenogKartona(Long idZdravstvenogKartona) {
		this.idZdravstvenogKartona = idZdravstvenogKartona;
	}


	public Long getIdKlinike() {
		return idKlinike;
	}


	public void setIdKlinike(Long idKlinike) {
		this.idKlinike = idKlinike;
	}


	

}
