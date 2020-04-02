package com.konyvtar.tag;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Tag {

	
	private int id;
	private String nev;
	private String cim;
	private String telefonszam;
	
	public Tag() {
		
	}
	
	public Tag(int id, String nev, String cim, String telefonszam) {
		this.id = id;
		this.nev = nev;
		this.cim = cim;
		this.telefonszam = telefonszam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public String getCim() {
		return cim;
	}

	public void setCim(String cim) {
		this.cim = cim;
	}

	public String getTelefonszam() {
		return telefonszam;
	}

	public void setTelefonszam(String telefonszam) {
		this.telefonszam = telefonszam;
	}
	
	
	
	
}
