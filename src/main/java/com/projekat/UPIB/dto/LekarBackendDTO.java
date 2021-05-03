package com.projekat.UPIB.dto;

import java.io.Serializable;
import java.util.HashSet;

import com.projekat.UPIB.enums.TipKorisnika;
import com.projekat.UPIB.models.Lekar;

public class LekarBackendDTO implements Serializable{
	
    private String imeKorisnika;
    private String prezimeKorisnika;
    private String lozinkaKorisnika;
    private String emailKorisnika;
    private TipKorisnika tipKorisnika;
    private Long idKlinike;
    HashSet<Long> pregledi;
    
	public LekarBackendDTO() {
		super();
	}

	
	public LekarBackendDTO(String imeKorisnika, String prezimeKorisnika, String lozinkaKorisnika,
			String emailKorisnika, TipKorisnika tipKorisnika, Long idKlinike) {
		super();
		this.imeKorisnika = imeKorisnika;
		this.prezimeKorisnika = prezimeKorisnika;
		this.lozinkaKorisnika = lozinkaKorisnika;
		this.emailKorisnika = emailKorisnika;
		this.tipKorisnika = tipKorisnika;
		this.idKlinike = idKlinike;
	}
	
	public LekarBackendDTO(String imeKorisnika, String prezimeKorisnika, String lozinkaKorisnika,
			String emailKorisnika, TipKorisnika tipKorisnika, Long idKlinike, HashSet<Long> pregledi) {
		super();
		this.imeKorisnika = imeKorisnika;
		this.prezimeKorisnika = prezimeKorisnika;
		this.lozinkaKorisnika = lozinkaKorisnika;
		this.emailKorisnika = emailKorisnika;
		this.tipKorisnika = tipKorisnika;
		this.idKlinike = idKlinike;
		this.pregledi = pregledi;
	}

	public LekarBackendDTO(Lekar lekar) {
		super();
		this.imeKorisnika = lekar.getImeKorisnika();
		this.prezimeKorisnika = lekar.getPrezimeKorisnika();
		this.lozinkaKorisnika = lekar.getLozinkaKorisnika();
		this.emailKorisnika = lekar.getEmailKorisnika();
		this.tipKorisnika = lekar.getTipKorisnika();
		this.idKlinike = lekar.getKlinika().getIdKlinike();
	}


	
	

	public HashSet<Long> getPregledi() {
		return pregledi;
	}


	public void setPregledi(HashSet<Long> pregledi) {
		this.pregledi = pregledi;
	}


	public String getLozinkaKorisnika() {
		return lozinkaKorisnika;
	}

	public void setLozinkaKorisnika(String lozinkaKorisnika) {
		this.lozinkaKorisnika = lozinkaKorisnika;
	}

	public Long getIdKlinike() {
		return idKlinike;
	}

	public void setIdKlinike(Long idKlinike) {
		this.idKlinike = idKlinike;
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

	public TipKorisnika getTipKorisnika() {
		return tipKorisnika;
	}

	public void setTipKorisnika(TipKorisnika tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}  

	
}
