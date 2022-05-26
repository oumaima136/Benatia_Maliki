package com.devoir.benatia_maliki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devoir.benatia_maliki.model.Ticket;
import com.devoir.benatia_maliki.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	 User findByUsername(String name);
	 
	 @Query(value="Select u.username from User u, users_roles ur  where ur.user_id=u.id and ur.role_id like 2", nativeQuery = true)
		public List<String> userDev();

	} 
