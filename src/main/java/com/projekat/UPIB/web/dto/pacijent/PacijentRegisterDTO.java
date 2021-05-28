package com.projekat.UPIB.web.dto.pacijent;

import java.io.Serializable;

import com.projekat.UPIB.enums.StatusKorisnika;
import com.projekat.UPIB.models.Pacijent;

public class PacijentRegisterDTO implements Serializable {

    private long id;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private String JBZO;
    private StatusKorisnika statusKorisnika;
    
	public PacijentRegisterDTO() {
		super();
	}	

	public PacijentRegisterDTO(long id, String ime, String prezime, String email, String lozinka, String jBZO,
			 StatusKorisnika statusKorisnika) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.JBZO = jBZO;
		this.statusKorisnika = statusKorisnika;
	}

	public PacijentRegisterDTO(Pacijent pacijent) {
		super();
		this.id = pacijent.getIdKorisnika();
		this.ime = pacijent.getImeKorisnika();
		this.prezime = pacijent.getPrezimeKorisnika();
		this.email = pacijent.getEmailKorisnika();
		this.lozinka = pacijent.getLozinkaKorisnika();
		this.JBZO = pacijent.getJBZO();
		this.statusKorisnika = pacijent.getStatusKorisnika();
	}

	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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

	public StatusKorisnika getStatusKorisnika() {
		return statusKorisnika;
	}

	public void setStatusKorisnika(StatusKorisnika statusKorisnika) {
		this.statusKorisnika = statusKorisnika;
	}
    

}
