package com.projekat.UPIB.dto;

import java.io.Serializable;

public class ReceptDTO implements Serializable{
	
	private Long idRecepta;
	private PregledDTO pregled;
	private boolean overen = false;
	
	
	public ReceptDTO() {
		super();
	}

	public ReceptDTO(Long idRecepta, PregledDTO pregled, boolean overen) {
		super();
		this.idRecepta = idRecepta;
		this.pregled = pregled;
		this.overen = overen;
	}

	
	
	public Long getIdRecepta() {
		return idRecepta;
	}

	public void setIdRecepta(Long idRecepta) {
		this.idRecepta = idRecepta;
	}

	public PregledDTO getPregled() {
		return pregled;
	}

	public void setPregled(PregledDTO pregled) {
		this.pregled = pregled;
	}

	public boolean isOveren() {
		return overen;
	}

	public void setOveren(boolean overen) {
		this.overen = overen;
	}
	
}
