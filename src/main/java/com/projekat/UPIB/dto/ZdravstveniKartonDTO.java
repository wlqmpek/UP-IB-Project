package com.projekat.UPIB.dto;

import java.io.Serializable;

public class ZdravstveniKartonDTO implements Serializable{
	
	private Long idZdravstvenogKartona;
	private PacijentRegisterDTO pacijent;
	
	
	public ZdravstveniKartonDTO() {
		super();
	}

	public ZdravstveniKartonDTO(Long idZdravstvenogKartona, PacijentRegisterDTO pacijent) {
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

	public PacijentRegisterDTO getPacijent() {
		return pacijent;
	}

	public void setPacijent(PacijentRegisterDTO pacijent) {
		this.pacijent = pacijent;
	}
	

}
