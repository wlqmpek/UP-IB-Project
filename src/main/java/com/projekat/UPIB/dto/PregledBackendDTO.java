package com.projekat.UPIB.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;

public class PregledBackendDTO implements Serializable{

    private String dijagnoza;
    private String opis;
    private Long idLekara;
    private Long idMedicinskeSestre;
    private LocalDateTime pocetakTermina;
    private LocalDateTime krajTermina;
    private double cena;
    private int popust;
    private Long idZdravstvenogKartona;
    private Long idKlinike;
    
    
	public PregledBackendDTO() {
		super();
	}




	public PregledBackendDTO(String dijagnoza, String opis, Long idLekara,
			Long idMedicinskeSestre, LocalDateTime pocetakTermina, LocalDateTime krajTermina, double cena, int popust,
			Long idZdravstvenogKartona, Long idKlinike) {
		super();
		this.dijagnoza = dijagnoza;
		this.opis = opis;
		this.idLekara = idLekara;
		this.idMedicinskeSestre = idMedicinskeSestre;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.cena = cena;
		this.popust = popust;
		this.idZdravstvenogKartona = idZdravstvenogKartona;
		this.idKlinike = idKlinike;
	}



	

	
	
	public LocalDateTime getKrajTermina() {
		return krajTermina;
	}

	public void setKrajTermina(LocalDateTime krajTermina) {
		this.krajTermina = krajTermina;
	}

	public LocalDateTime getPocetakTermina() {
		return pocetakTermina;
	}

	public void setPocetakTermina(LocalDateTime pocetakTermina) {
		this.pocetakTermina = pocetakTermina;
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
