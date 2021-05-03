package com.projekat.UPIB.dto;

import java.io.Serializable;

import com.projekat.UPIB.enums.TipKorisnika;
import com.projekat.UPIB.models.Lekar;

public class LekarFrontendDTO implements Serializable{
	
	private Long idKorisnika;
    private String imeKorisnika;
    private String prezimeKorisnika;
    private String emailKorisnika;
    private TipKorisnika tipKorisnika;
    private Long idKlinike;
    
	public LekarFrontendDTO() {
		super();
	}

	public LekarFrontendDTO(Lekar lekar) {
		super();
		this.idKorisnika = lekar.getIdKorisnika();
		this.imeKorisnika = lekar.getImeKorisnika();
		this.prezimeKorisnika = lekar.getPrezimeKorisnika();
		this.emailKorisnika = lekar.getEmailKorisnika();
		this.tipKorisnika = lekar.getTipKorisnika();
		this.idKlinike = lekar.getKlinika().getIdKlinike();
	}



	public Long getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(Long idKorisnika) {
		this.idKorisnika = idKorisnika;
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
