package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.services.VetService;

@Controller
public class VetController {
	
	private VetService vetService;
	
	public VetController(VetService vetService) {
		super();
		this.vetService = vetService;
	}

	@RequestMapping({"/vets", "/vets.html"})
	public String listVets(Model model) {
		model.addAttribute("vets", vetService.findAll());
		
		return "vets/index";
	}
}
