package com.projekat.UPIB.dto;

import java.io.Serializable;

public class LekarDTO implements Serializable{
	
	private long idKorisnika;
    private String imeKorisnika;
    private String prezimeKorisnika;
    private String emailKorisnika;
    private String lozinkaKorisnika;
    private KlinikaDTO klinika;
    
	public LekarDTO() {
		super();
	}

	public LekarDTO(long idKorisnika, String imeKorisnika, String prezimeKorisnika, String emailKorisnika,
			String lozinkaKorisnika, KlinikaDTO klinika) {
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

	public KlinikaDTO getKlinika() {
		return klinika;
	}

	public void setKlinika(KlinikaDTO klinika) {
		this.klinika = klinika;
	}
    

}
