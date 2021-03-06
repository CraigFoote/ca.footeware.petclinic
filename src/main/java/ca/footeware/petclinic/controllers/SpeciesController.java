/**
 * 
 */
package ca.footeware.petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.models.Species;
import ca.footeware.petclinic.services.SpeciesService;

/**
 * @author Footeware.ca
 *
 */
@Controller
@RequestMapping("/species")
public class SpeciesController {

	@Autowired
	private SpeciesService speciesService;

	@GetMapping("/add")
	String getAddSpeciesPage() {
		return "addSpecies";
	}

	@PostMapping("/add")
	String createSpecies(@RequestParam("name") String name, Model model) {
		speciesService.saveSpecies(new Species(name));
		model.addAttribute("species", speciesService.getAllSpecies());
		return "addPet";
	}
}
