package com.projekat.UPIB.dto;

import java.io.Serializable;

import com.projekat.UPIB.enums.VrstaAdministratora;

public class AdministratorBackendDTO implements Serializable{
	
    private String imeKorisnika;
    private String prezimeKorisnika;
    private String emailKorisnika;
    private String lozinkaKorisnika;
    private Long idKlinike;
    private VrstaAdministratora vrstaAdministratora;
    
    
    
	public AdministratorBackendDTO() {
		super();
	}
	
	public AdministratorBackendDTO(String imeKorisnika, String prezimeKorisnika, String emailKorisnika,
			String lozinkaKorisnika, VrstaAdministratora vrstaAdministratora) {
		super();
		this.imeKorisnika = imeKorisnika;
		this.prezimeKorisnika = prezimeKorisnika;
		this.emailKorisnika = emailKorisnika;
		this.lozinkaKorisnika = lozinkaKorisnika;
		this.vrstaAdministratora = vrstaAdministratora;
	}

	public AdministratorBackendDTO(String imeKorisnika, String prezimeKorisnika, String emailKorisnika,
			String lozinkaKorisnika, Long idKlinike, VrstaAdministratora vrstaAdministratora) {
		super();
		this.imeKorisnika = imeKorisnika;
		this.prezimeKorisnika = prezimeKorisnika;
		this.emailKorisnika = emailKorisnika;
		this.lozinkaKorisnika = lozinkaKorisnika;
		this.idKlinike = idKlinike;
		this.vrstaAdministratora = vrstaAdministratora;
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
