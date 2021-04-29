package com.projekat.UPIB.dto;

import java.io.Serializable;

import com.projekat.UPIB.enums.VrstaAdministratora;
import com.projekat.UPIB.models.Administrator;

public class AdministratorFrontendDTO implements Serializable{
	
	private Long idKorisnika;
    private String imeKorisnika;
    private String prezimeKorisnika;
    private String emailKorisnika;
    private Long idKlinike;
    private VrstaAdministratora vrstaAdministratora;
    
    
    
	public AdministratorFrontendDTO() {
		super();
	}

	public AdministratorFrontendDTO(long idKorisnika, String imeKorisnika, String prezimeKorisnika, String emailKorisnika,
			Long idKlinike, VrstaAdministratora vrstaAdministratora) {
		super();
		this.idKorisnika = idKorisnika;
		this.imeKorisnika = imeKorisnika;
		this.prezimeKorisnika = prezimeKorisnika;
		this.emailKorisnika = emailKorisnika;
		this.idKlinike = idKlinike;
		this.vrstaAdministratora = vrstaAdministratora;
	}
	
	public AdministratorFrontendDTO(Administrator admin) {
		super();
		this.idKorisnika = admin.getIdKorisnika();
		this.imeKorisnika = admin.getImeKorisnika();
		this.prezimeKorisnika = admin.getPrezimeKorisnika();
		this.emailKorisnika = admin.getEmailKorisnika();
		if (admin.getKlinika() != null) {
			this.idKlinike = admin.getKlinika().getIdKlinike();
		}
		this.vrstaAdministratora = admin.getVrstaAdministratora();
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
	public Long getIdKlinike() {
		return idKlinike;
	}
	public void setIdKlinike(Long idKlinike) {
		this.idKlinike = idKlinike;
	}
	public VrstaAdministratora getVrstaAdministratora() {
		return vrstaAdministratora;
	}
	public void setVrstaAdministratora(VrstaAdministratora vrstaAdministratora) {
		this.vrstaAdministratora = vrstaAdministratora;
	}	
    

}
