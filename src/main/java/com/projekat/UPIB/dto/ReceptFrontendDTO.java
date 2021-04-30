package com.projekat.UPIB.dto;

import java.io.Serializable;

import com.projekat.UPIB.models.Recept;

public class ReceptFrontendDTO implements Serializable{
	
	private Long idRecepta;
	private Long idPregleda;
	private String opisRecepta;
	private boolean overen = false;
	
	
	public ReceptFrontendDTO() {
		super();
	}

	public ReceptFrontendDTO(Long idRecepta, Long idPregleda, String opisRecepta, boolean overen) {
		super();
		this.idRecepta = idRecepta;
		this.opisRecepta = opisRecepta;
		this.idPregleda = idPregleda;
		this.overen = overen;
	}

	public ReceptFrontendDTO(Recept recept) {
		super();
		this.idRecepta = recept.getIdRecepta();
		this.opisRecepta = recept.getOpisRecepta();
		this.idPregleda = recept.getPregled().getIdPregleda();
		this.overen = recept.isOveren();
	}

	public Long getIdRecepta() {
		return idRecepta;
	}

	public void setIdRecepta(Long idRecepta) {
		this.idRecepta = idRecepta;
	}

	public Long getIdPregleda() {
		return idPregleda;
	}

	public void setIdPregleda(Long idPregleda) {
		this.idPregleda = idPregleda;
	}

	public String getOpisRecepta() {
		return opisRecepta;
	}

	public void setOpisRecepta(String opisRecepta) {
		this.opisRecepta = opisRecepta;
	}

	public boolean isOveren() {
		return overen;
	}

	public void setOveren(boolean overen) {
		this.overen = overen;
	}
	
	
	
	
}
