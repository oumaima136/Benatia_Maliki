package com.devoir.benatia_maliki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devoir.benatia_maliki.model.Role;


public interface RoleRepository extends JpaRepository<Role,Integer>{
	
	 public Role findByNom(String nom);
}
	
