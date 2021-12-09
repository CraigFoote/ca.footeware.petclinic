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

import ca.footeware.petclinic.exceptions.LostPersonException;
import ca.footeware.petclinic.models.Owner;
import ca.footeware.petclinic.models.Person;
import ca.footeware.petclinic.services.OwnerService;

/**
 * @author Footeware.ca
 *
 */
@Controller
@RequestMapping("/owners")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;

	@GetMapping("/addOwner")
	public String getAddOwnerPage(Model model) {
		return "addOwner";
	}

	@PostMapping("/addOwner")
	public String createOwner(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("phone") String phone, Model model)
			throws LostPersonException {
		Person owner = new Owner(firstName, lastName, email, phone);
		Person savedOwner = ownerService.saveOwner(owner);
		if (savedOwner == null) {
			throw new LostPersonException("Saved owner not found.");
		}
		model.addAttribute("owners", ownerService.getOwners());
		return "owners";
	}

	@GetMapping("{id}")
	String getOwner(@PathVariable String id, Model model) {
		Person owner = ownerService.getById(id);
		model.addAttribute("owner", owner);
		return "owner";
	}

	@GetMapping
	String getOwners(Model model) {
		List<Person> owners = ownerService.getOwners();
		owners.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("owners", owners);
		return "owners";
	}

	@GetMapping("/{id}/edit")
	String editOwner(@PathVariable String id, Model model) {
		Person owner = ownerService.getById(id);
		model.addAttribute("owner", owner);
		return "editOwner";
	}

	@PostMapping("/edit")
	String updateOwner(@RequestParam("id") String id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam(name = "petIds", required = false) List<String> petIds,
			Model model) {
		Person owner = ownerService.getById(id);
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setEmail(email);
		owner.setPhone(phone);
		ownerService.saveOwner(owner);
		return getOwners(model);
	}

}
