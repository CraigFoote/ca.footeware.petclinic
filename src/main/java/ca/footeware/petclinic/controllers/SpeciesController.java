/**
 *
 */
package ca.footeware.petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PostMapping
	String createSpecies(@RequestParam(name = "name", required = true) String name, Model model) {
		speciesService.save(new Species(name));
		return getAllSpecies(model);
	}

	@GetMapping
	public String getAllSpecies(Model model) {
		model.addAttribute("allSpecies", speciesService.getAll());
		return "species";
	}
	
	@DeleteMapping("{id}")
	public String deleteSpecies(@PathVariable("id") String id, Model model) {
		speciesService.delete(id);
		return getAllSpecies(model);
	}
}
