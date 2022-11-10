/**
 *
 */
package ca.footeware.petclinic.controllers;

import java.util.ArrayList;
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

import ca.footeware.petclinic.exceptions.PersonException;
import ca.footeware.petclinic.models.Vet;
import ca.footeware.petclinic.services.VetService;

/**
 * @author Footeware.ca
 *
 */
@Controller
@RequestMapping("/vets")
public class VetController {

	@Autowired
	private VetService vetService;

	@GetMapping("/add")
	public String getAddVetPage(Model model) {
		return "addVet";
	}

	@PostMapping
	public String createVet(@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "phone", required = true) String phone, Model model) throws PersonException {
		Vet vet = new Vet(firstName, lastName, email, phone);
		Vet savedVet = vetService.save(vet);
		if (savedVet == null) {
			throw new PersonException("Saved vet not found.");
		}
		model.addAttribute("vets", vetService.getAll());
		return "vets";
	}

	@GetMapping("{id}")
	String getVet(@PathVariable int id, Model model) {
		Vet vet = vetService.get(id);
		model.addAttribute("vet", vet);
		return "vet";
	}

	@GetMapping
	String getVets(Model model) {
		Iterable<Vet> vets = vetService.getAll();
		List<Vet> vetsList = new ArrayList<>();
		for (Vet vet : vets) {
			vetsList.add(vet);
		}
		vetsList.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("vets", vetsList);
		return "vets";
	}

	@GetMapping("/{id}/edit")
	String editVet(@PathVariable int id, Model model) {
		Vet vet = vetService.get(id);
		model.addAttribute("vet", vet);
		return "editVet";
	}

	@PostMapping("/edit")
	String updateVet(@RequestParam(name = "id", required = true) int id,
			@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "phone", required = true) String phone, Model model) throws PersonException {
		Vet vet = vetService.get(id);
		vet.setFirstName(firstName);
		vet.setLastName(lastName);
		vet.setEmail(email);
		vet.setPhone(phone);
		Vet savedVet = vetService.save(vet);
		if (savedVet == null) {
			throw new PersonException("Saved vet not found.");
		}
		return getVets(model);
	}

	@DeleteMapping("/{id}")
	public String deleteOwner(@PathVariable("id") int id, Model model) {
		vetService.delete(id);
		return getVets(model);
	}

}
