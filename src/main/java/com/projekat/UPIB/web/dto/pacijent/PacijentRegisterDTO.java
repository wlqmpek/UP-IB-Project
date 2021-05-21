package com.projekat.UPIB.web.dto.pacijent;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Authority;
import com.projekat.UPIB.models.Pacijent;
import com.projekat.UPIB.services.implementation.AuthorityService;
import lombok.ToString;

@ToString
public class PacijentRegisterDTO implements Serializable {

    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private String ponovljenaLozinka;
	@JsonProperty(value = "JBZO")
    private String JBZO;
	private Long authorities = 4L;
    
	public PacijentRegisterDTO() {
		super();
	}

	public PacijentRegisterDTO(String ime, String prezime, String email, String lozinka, String ponovljenaLozinka, String JBZO, int authorities) {
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.ponovljenaLozinka = ponovljenaLozinka;
		this.JBZO = JBZO;
	}

	
	public String getIme() {
		return ime;
	}
	
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getPrezime() {
		return prezime;
	}
	
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
    
	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getJBZO() {
		return JBZO;
	}
	
	public void setJBZO(String jBZO) {
		JBZO = jBZO;
	}

	public String getPonovljenaLozinka() {
		return ponovljenaLozinka;
	}

	public void setPonovljenaLozinka(String ponovljenaLozinka) {
		this.ponovljenaLozinka = ponovljenaLozinka;
	}

	public Long getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Long authorities) {
		this.authorities = authorities;
	}
}
