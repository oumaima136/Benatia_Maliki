package com.devoir.benatia_maliki.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements Serializable , UserDetails {
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false, unique=true)
	private String username;
	@Column(nullable=false)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 @JoinTable(name = "users_roles",joinColumns = @JoinColumn(name = "user_id", referencedColumnName ="id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
	private List<Role> role;
	/*
	@OneToMany
	private List<Ticket> tickets;

	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	*/
	public int getId() {
		return id;
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
	
	public List<Role> getRole() {
		return role;
	}
	public void setRole(List<Role> role) {
		this.role = role;
	}
	
	
	public User(String userName, String password, List<Role> roles) {
		 this.username = userName;
		 this.password = password;
		 this.role = roles;
		 }
		 public User() {
		 }
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
	
	
}
