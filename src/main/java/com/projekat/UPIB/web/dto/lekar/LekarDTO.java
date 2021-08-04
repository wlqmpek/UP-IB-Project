package com.projekat.UPIB.web.dto.lekar;

import com.projekat.UPIB.web.dto.klinika.KlinikaFrontDto;

import java.io.Serializable;

public class LekarDTO implements Serializable{
	
	private long idKorisnika;
    private String imeKorisnika;
    private String prezimeKorisnika;
    private String emailKorisnika;
    private String lozinkaKorisnika;
    private KlinikaFrontDto klinika;
    
	public LekarDTO() {
		super();
	}

	public LekarDTO(long idKorisnika, String imeKorisnika, String prezimeKorisnika, String emailKorisnika,
			String lozinkaKorisnika, KlinikaFrontDto klinika) {
		super();
		this.idKorisnika = idKorisnika;
		this.imeKorisnika = imeKorisnika;
		this.prezimeKorisnika = prezimeKorisnika;
		this.emailKorisnika = emailKorisnika;
		this.lozinkaKorisnika = lozinkaKorisnika;
		this.klinika = klinika;
	}


	public long getIdKorisnika() {
		return idKorisnika;
	}
	
	public void setIdKorisnika(long idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
	
	public String getImeKorisnika() {
		return imeKorisnika;
	}
	
	public void setImeKorisnika(String imeKorisnika) {
		this.imeKorisnika = imeKorisnika;
	}
	
	public String getPrezimeKorisnika() {
		return prezimeKorisnika;
	}
	
	public void setPrezimeKorisnika(String prezimeKorisnika) {
		this.prezimeKorisnika = prezimeKorisnika;
	}
	
	public String getEmailKorisnika() {
		return emailKorisnika;
	}
	
	public void setEmailKorisnika(String emailKorisnika) {
		this.emailKorisnika = emailKorisnika;
	}
	
	public String getLozinkaKorisnika() {
		return lozinkaKorisnika;
	}
	
	public void setLozinkaKorisnika(String lozinkaKorisnika) {
		this.lozinkaKorisnika = lozinkaKorisnika;
	}

	public KlinikaFrontDto getKlinika() {
		return klinika;
	}

	public void setKlinika(KlinikaFrontDto klinika) {
		this.klinika = klinika;
	}
    

}
