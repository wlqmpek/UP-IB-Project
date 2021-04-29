package com.projekat.UPIB.dto;

import java.io.Serializable;

import com.projekat.UPIB.models.Klinika;

public class KlinikaDTO implements Serializable{
	
	private Long idKlinike;
    private String naziv;
    private String adresa;
    private String opis;
    

	public KlinikaDTO() {
		super();
	}

	public KlinikaDTO(Long idKlinike, String naziv, String adresa, String opis) {
		super();
		this.idKlinike = idKlinike;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
	}
	
	public KlinikaDTO(Klinika klinika) {
		super();
		this.idKlinike = klinika.getIdKlinike();
		this.naziv = klinika.getNaziv();
		this.adresa = klinika.getAdresa();
		this.opis = klinika.getOpis();
	}


	public Long getIdKlinike() {
		return idKlinike;
	}
	
	public void setIdKlinike(Long idKlinike) {
		this.idKlinike = idKlinike;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
    
    
    

}
