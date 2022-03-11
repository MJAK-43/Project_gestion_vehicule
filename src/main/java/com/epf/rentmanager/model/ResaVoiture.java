package com.epf.rentmanager.model;

import java.time.LocalDate;

public class ResaVoiture {

	private int id;
	private String voiture;
	private String client;
	private LocalDate dateStart;
	private LocalDate dateEnd;

	public ResaVoiture() {
		super();
	}

	public ResaVoiture(int id, String voiture, String client, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.id = id;
		this.voiture = voiture;
		this.client = client;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVoiture() {
		return voiture;
	}

	public void setVoiture(String voiture) {
		this.voiture = voiture;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

}
