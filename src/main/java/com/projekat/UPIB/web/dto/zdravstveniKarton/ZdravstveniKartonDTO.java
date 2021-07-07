package com.projekat.UPIB.web.dto.zdravstveniKarton;

import com.projekat.UPIB.models.ZdravstveniKarton;

import java.io.Serializable;

public class ZdravstveniKartonDTO implements Serializable{
	
	private Long idZdravstvenogKartona;
	
	public ZdravstveniKartonDTO() {
		super();
	}

	public ZdravstveniKartonDTO(ZdravstveniKarton zdravstveniKarton) {
		super();
		this.idZdravstvenogKartona = zdravstveniKarton.getIdZdravstvenogKartona();
	}
	
	public Long getIdZdravstvenogKartona() {
		return idZdravstvenogKartona;
	}

	public void setIdZdravstvenogKartona(Long idZdravstvenogKartona) {
		this.idZdravstvenogKartona = idZdravstvenogKartona;
	}
}
