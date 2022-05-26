package com.devoir.benatia_maliki.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Admin {
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false, unique=true)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@OneToMany
	private List<Ticket> tickets;

	public int getId() {
		return id;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
