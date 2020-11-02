/**
 * 
 */
package ca.footeware.petclinic.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.exceptions.LostOwnerException;
import ca.footeware.petclinic.models.Owner;
import ca.footeware.petclinic.models.Pet;
import ca.footeware.petclinic.models.Species;
import ca.footeware.petclinic.services.OwnerService;
import ca.footeware.petclinic.services.PetService;

/**
 * @author Footeware.ca
 *
 */
@Controller
@RequestMapping("/owners")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private PetService petService;

	@PostMapping
	String searchByLastName(@RequestParam("lastName") String lastName, Model model) {
		model.addAttribute("lastName", lastName);
		model.addAttribute("owners", ownerService.getByLastName(lastName));
		return "owners";
	}

	@GetMapping("/add")
	String getAddOwnerPage(Model model) {
		Map<Species, Set<Pet>> petMap = petService.getOwnerlessPets();
		model.addAttribute("availablePetsMap", petMap);
		return "addOwner";
	}

	@PostMapping("/add")
	String createOwner(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("phone") String phone, Model model)
			throws LostOwnerException {
		Owner owner = new Owner(firstName, lastName, email, phone);
		Owner savedOwner = ownerService.saveOwner(owner);
		if (savedOwner == null) {
			throw new LostOwnerException("Saved owner not found.");
		}
		model.addAttribute("owners", ownerService.getOwners());
		return "owners";
	}

	@GetMapping("{id}")
	String getUser(@PathVariable String id, Model model) {
		Owner owner = ownerService.getById(id);
		Set<String> petIds = owner.getPets();
		Set<Pet> pets = new HashSet<>();
		for (String petId : petIds) {
			Pet pet = petService.getPet(petId);
			pets.add(pet);
		}
		model.addAttribute("owner", owner);
		model.addAttribute("pets", pets);
		return "owner";
	}

	@GetMapping
	String getOwners(Model model) {
		List<Owner> owners = ownerService.getOwners();
		model.addAttribute("owners", owners);
		return "owners";
	}

	@GetMapping("/{id}/edit")
	String editOwner(@PathVariable String id, Model model) {
		Owner owner = ownerService.getById(id);
		model.addAttribute("owner", owner);
		Map<Species, Set<Pet>> petMap = petService.getOwnerlessPets();
		model.addAttribute("availablePetsMap", petMap);
		return "editOwner";
	}

	@PostMapping("/edit")
	String updateOwner(@RequestParam("id") String id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("phone") String phone, Model model) {
		Owner owner = ownerService.getById(id);
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setEmail(email);
		owner.setPhone(phone);
		ownerService.saveOwner(owner);
		return getOwners(model);
	}

}
