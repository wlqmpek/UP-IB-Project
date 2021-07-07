package com.projekat.UPIB.web.dto.zdravstveniKarton;

import com.projekat.UPIB.models.ZdravstveniKarton;

import java.io.Serializable;

public class ZdravstveniKartonBackendDTO implements Serializable{
	
	private int visina;
	private int tezina;
	private String krvnaGrupa;
	private double dioptrija;
	private String alergije;
	
	
	public ZdravstveniKartonBackendDTO() {
		super();
	}



	public ZdravstveniKartonBackendDTO(int visina, int tezina, String krvnaGrupa,
			double dioptrija, String alergije) {
		super();
		this.visina = visina;
		this.tezina = tezina;
		this.krvnaGrupa = krvnaGrupa;
		this.dioptrija = dioptrija;
		this.alergije = alergije;
	}

	public ZdravstveniKartonBackendDTO(ZdravstveniKarton zdravstveniKarton) {
		super();
		this.visina = zdravstveniKarton.getVisina();
		this.tezina = zdravstveniKarton.getTezina();
		this.krvnaGrupa = zdravstveniKarton.getKrvnaGrupa();
		this.dioptrija = zdravstveniKarton.getDioptrija();
		this.alergije = zdravstveniKarton.getAlergije();
	}


	

	


	public String getAlergije() {
		return alergije;
	}



	public void setAlergije(String alergije) {
		this.alergije = alergije;
	}



	public int getVisina() {
		return visina;
	}



	public void setVisina(int visina) {
		this.visina = visina;
	}



	public int getTezina() {
		return tezina;
	}



	public void setTezina(int tezina) {
		this.tezina = tezina;
	}



	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}



	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}



	public double getDioptrija() {
		return dioptrija;
	}



	public void setDioptrija(double dioptrija) {
		this.dioptrija = dioptrija;
	}
	
	

}
