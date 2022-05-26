package com.devoir.benatia_maliki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devoir.benatia_maliki.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer>{
	
	@Query("Select t from Ticket t where Attribue like 'non atribuée' ")
	public List<Ticket> ticketsNonAtribue();
	
	@Query("Select u from Ticket u where u.client.username like ?1")
	public List<Ticket> ticketsClient(String c);
	
	@Query("Select u from Ticket u where u.devlop.username like ?1")
	public List<Ticket> ticketsDev(String c);

	
}