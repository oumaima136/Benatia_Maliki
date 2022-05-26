package com.devoir.benatia_maliki.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {
	@Id
	@GeneratedValue
	private int id;
	private String description;
	private String urgence;
	private String environnement;
	private String logiciel;
	private Boolean resolu;
	private String Attribue;
	@ManyToOne
	private User client;
	@ManyToOne
	private User devlop; 
	
	
	
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public User getDevlop() {
		return devlop;
	}
	public void setDevlop(User devlop) {
		this.devlop = devlop;
	}
	public String getAttribue() {
		return Attribue;
	}
	public void setAttribue(String attribue) {
		Attribue = attribue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrgence() {
		return urgence;
	}
	public void setUrgence(String urgence) {
		this.urgence = urgence;
	}
	public String getEnvironnement() {
		return environnement;
	}
	public void setEnvironnement(String environnement) {
		this.environnement = environnement;
	}
	public String getLogiciel() {
		return logiciel;
	}
	public void setLogiciel(String logiciel) {
		this.logiciel = logiciel;
	}
	public Boolean getResolu() {
		return resolu;
	}
	public void setResolu(Boolean resolu) {
		this.resolu = resolu;
	}
	
	

}
