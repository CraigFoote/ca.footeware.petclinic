/**
 * 
 */
package ca.footeware.petclinic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.exceptions.PersonException;
import ca.footeware.petclinic.models.Vet;
import ca.footeware.petclinic.models.Person;
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

	@PostMapping("/add")
	public String createVet(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("phone") String phone, Model model)
			throws LostPersonException {
		Vet vet = new Vet(firstName, lastName, email, phone);
		Vet savedVet = vetService.save(vet);
		if (savedVet == null) {
			throw new PersonException("Saved vet not found.");
		}
		model.addAttribute("vets", vetService.getAll());
		return "vets";
	}

	@GetMapping("{id}")
	String getVet(@PathVariable String id, Model model) {
		Person vet = vetService.get(id);
		model.addAttribute("vet", vet);
		return "vet";
	}

	@GetMapping
	String getVets(Model model) {
		List<Doctor> vets = vetService.getAll();
		vets.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("vets", vets);
		return "vets";
	}

	@GetMapping("/{id}/edit")
	String editVet(@PathVariable String id, Model model) {
		Person vet = vetService.get(id);
		model.addAttribute("vet", vet);
		return "editVet";
	}

	@PostMapping("/edit")
	String updateVet(@RequestParam("id") String id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("phone") String phone,
			Model model) {
		Vet vet = vetService.get(id);
		vet.setFirstName(firstName);
		vet .setLastName(lastName);
		vet.setEmail(email);
		vet.setPhone(phone);
		vetService.save(vet);
		return getVets(model);
	}

}
