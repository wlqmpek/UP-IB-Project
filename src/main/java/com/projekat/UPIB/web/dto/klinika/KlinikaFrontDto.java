package com.projekat.UPIB.web.dto.klinika;

import java.io.Serializable;

import com.projekat.UPIB.models.Klinika;
import com.projekat.UPIB.services.implementation.OceneKlinikeService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@ToString
public class KlinikaFrontDto implements Serializable {

	@Autowired
	private OceneKlinikeService oceneKlinikeService;
	
	private Long idKlinike;
    private String naziv;
    private String adresa;
    private String opis;
    private Double ocena;
    

	public KlinikaFrontDto() {
		super();
	}

	public KlinikaFrontDto(Long idKlinike, String naziv, String adresa, String opis) {
		super();
		this.idKlinike = idKlinike;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
	}
	
	public KlinikaFrontDto(Klinika klinika) {
		super();
		this.idKlinike = klinika.getIdKlinike();
		this.naziv = klinika.getNaziv();
		this.adresa = klinika.getAdresa();
		this.opis = klinika.getOpis();
		this.ocena = oceneKlinikeService.avgOcena(klinika.getIdKlinike());
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

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}
}
