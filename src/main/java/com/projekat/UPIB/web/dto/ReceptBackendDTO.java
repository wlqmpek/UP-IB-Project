package com.projekat.UPIB.web.dto;

import java.io.Serializable;

import com.projekat.UPIB.models.Recept;

public class ReceptBackendDTO implements Serializable{
	
	private Long idPregleda;
	private String opisRecepta;
	private boolean overen;
	
	
	public ReceptBackendDTO() {
		super();
	}

	public ReceptBackendDTO(Long idPregleda, String opisRecepta, boolean overen) {
		super();
		this.idPregleda = idPregleda;
		this.opisRecepta = opisRecepta;
		this.overen = overen;
	}

	public ReceptBackendDTO(Recept recept) {
		super();
		this.idPregleda = recept.getPregled().getIdPregleda();
		this.opisRecepta = recept.getOpisRecepta();
		this.overen = recept.isOveren();
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
