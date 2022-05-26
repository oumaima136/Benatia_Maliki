package com.devoir.benatia_maliki.controller;

import java.util.List;


import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import com.devoir.benatia_maliki.model.Ticket;
import com.devoir.benatia_maliki.model.User;
import com.devoir.benatia_maliki.repository.TicketRepository;
import com.devoir.benatia_maliki.repository.UserRepository;
import com.devoir.benatia_maliki.services.UserService;

@Controller 
@RequestMapping("/")
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TicketRepository trep;

	@GetMapping
	public String index(Model m) {
		m.addAttribute("ticketsnonatr",listNonAtribuee());
		m.addAttribute("ticketClient",listClient());
		m.addAttribute("ticketDev",listDev());
		m.addAttribute("dev",0);
		m.addAttribute("ticket",0);
		m.addAttribute("userDev",loadDeveloppeur());
		return "layout";
	}
	
/*	@GetMapping("/index")
	public String layout() {
		return "index";
	}*/

	@GetMapping("login")
	public String login() {
		return "connexion";
	}
	
	
	public List<Ticket> listNonAtribuee() {
		List<Ticket> tickets = trep.ticketsNonAtribue();
		return tickets;
	}
	
	public List<Ticket> listClient() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		List<Ticket> tickets = trep.ticketsClient(auth.getName());
		return tickets;
	}
	
	public List<Ticket> listDev() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Ticket> tickets = trep.ticketsDev(auth.getName());
		return tickets;
	}
	

	@GetMapping("/inscription")
	public String insc(Model m) {
		m.addAttribute("user", new User());
		return "inscription";
	}

	@PostMapping("/inscription")
	public String insc(@ModelAttribute User user, BindingResult result) {
		if (result.hasErrors())
			return "inscription";
		userService.save(user);
	
		return "redirect:login";
	}
	
	@PostMapping("/{id}")
	public String atribuer(@ModelAttribute("dev") User u, @PathVariable int id) {
		Ticket t = trep.findById(id).get();
		//System.out.println(u.getId());
		User dev = userService.getUser(u.getId());
		//System.out.println("hjgyygigyiygoguygo"+dev.getId());
		t.setDevlop(dev);
		t.setAttribue("en cours de traitement");
		trep.save(t);
		return "redirect:";
	}
	
	@PostMapping("/ticket")
	public String creer(@ModelAttribute ("cree") Ticket u) {
		System.out.println(u.getDescription());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		u.setResolu(false);
		u.setClient(userRepository.findByUsername(auth.getName()));
		u.setAttribue("non atribuée");
		trep.save(u);
		return "redirect:";
	}
	
	@GetMapping("/creerTicket")
	public String creerTicketPage(Model m) {
		m.addAttribute("cree", new Ticket());
		return "creeTicket";
	}

	@PostMapping("/update/{id}")
	public String update(@ModelAttribute("ticket") Ticket u , @PathVariable int id) {
		Ticket t = trep.findById(id).get();
		t.setAttribue("Résolu");
		t.setResolu(true);
		trep.save(t);
		return "redirect:/";
	}
	


	
	public List<String> loadDeveloppeur() {
		return	userRepository.userDev();
	}

}