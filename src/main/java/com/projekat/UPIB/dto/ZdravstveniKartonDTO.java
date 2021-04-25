package com.projekat.UPIB.dto;

import java.io.Serializable;

public class ZdravstveniKartonDTO implements Serializable{
	
	private Long idZdravstvenogKartona;
	private PacijentDTO pacijent;
	
	
	public ZdravstveniKartonDTO() {
		super();
	}

	public ZdravstveniKartonDTO(Long idZdravstvenogKartona, PacijentDTO pacijent) {
		super();
		this.idZdravstvenogKartona = idZdravstvenogKartona;
		this.pacijent = pacijent;
	}

	
	
	public Long getIdZdravstvenogKartona() {
		return idZdravstvenogKartona;
	}

	public void setIdZdravstvenogKartona(Long idZdravstvenogKartona) {
		this.idZdravstvenogKartona = idZdravstvenogKartona;
	}

	public PacijentDTO getPacijent() {
		return pacijent;
	}

	public void setPacijent(PacijentDTO pacijent) {
		this.pacijent = pacijent;
	}
	

}
