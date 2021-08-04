package com.projekat.UPIB.web.dto.lekar;
import java.io.Serializable;
import com.projekat.UPIB.models.Lekar;
import com.projekat.UPIB.services.implementation.LekarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class LekarFrontendDTO implements Serializable{

	private Long idKorisnika;
    private String imeKorisnika;
    private String prezimeKorisnika;
    private String emailKorisnika;
    private Long idKlinike;
    private Double ocena;

	public LekarFrontendDTO() {
		super();
	}

	public LekarFrontendDTO(Lekar lekar) {
		super();
		this.idKorisnika = lekar.getIdKorisnika();
		this.imeKorisnika = lekar.getImeKorisnika();
		this.prezimeKorisnika = lekar.getPrezimeKorisnika();
		this.emailKorisnika = lekar.getEmailKorisnika();
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

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}
}
