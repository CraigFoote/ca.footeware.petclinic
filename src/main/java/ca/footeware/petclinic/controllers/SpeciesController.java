/**
 *
 */
package ca.footeware.petclinic.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.exceptions.SpeciesException;
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

	@PostMapping
	public String createSpecies(@RequestParam(name = "name", required = true) String name, Model model)
			throws SpeciesException {
		Species saved = speciesService.save(new Species(name));
		if (saved == null) {
			throw new SpeciesException("'" + name + "' did not save");
		}
		return getAllSpecies(model);
	}

	@DeleteMapping("/{id}")
	public String deleteSpecies(@PathVariable("id") int id, Model model) {
		speciesService.delete(id);
		return getAllSpecies(model);
	}

	@GetMapping("/{id}")
	public String editSpecies(@PathVariable int id, Model model) {
		Species species = speciesService.get(id);
		model.addAttribute("species", species);
		return "editSpecies";
	}

	@GetMapping("/add")
	public String getAddSpeciesPage() {
		return "addSpecies";
	}

	@GetMapping
	public String getAllSpecies(Model model) {
		// FIXME, necessary to get accurate inventory
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterable<Species> allSpecies = speciesService.getAll();
		List<Species> allSpeciesList = new ArrayList<>();
		for (Species species : allSpecies) {
			allSpeciesList.add(species);
		}
		Collections.sort(allSpeciesList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("allSpecies", allSpeciesList);
		return "species";
	}

	@PostMapping("/update")
	public String updateSpecies(@RequestParam(name = "id", required = true) int id,
			@RequestParam(name = "name", required = true) String name, Model model) throws SpeciesException {
		Species species = speciesService.get(id);
		species.setName(name);
		Species savedSpecies = speciesService.save(species);
		if (savedSpecies == null) {
			throw new SpeciesException("'" + name + "' did not save.");
		}
		return getAllSpecies(model);
	}
}
