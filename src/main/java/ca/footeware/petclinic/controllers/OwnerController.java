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
import ca.footeware.petclinic.models.Owner;
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

	@PostMapping
	public String createOwner(@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "phone", required = true) String phone, Model model) throws PersonException {
		List<Owner> firstNameAndLastName = ownerService.findByFirstNameAndLastName(firstName, lastName);
		if (firstNameAndLastName.size() > 0) {
			for (Owner owner : firstNameAndLastName) {
				if (owner.getEmail().equals(email)) {
					String message = "A person already exists by that name and email.";
					model.addAttribute("errorMessage", message);
					throw new PersonException(message);
				} else if (owner.getPhone().equals(phone)) {
					String message = "A person already exists by that name and phone number.";
					model.addAttribute("errorMessage", message);
					throw new PersonException(message);
				}
			}
		}
		Owner owner = new Owner(firstName, lastName, email, phone);
		Owner savedOwner = ownerService.save(owner);
		if (savedOwner == null) {
			throw new PersonException("Saved owner not found.");
		}
		model.addAttribute("owners", ownerService.getAll());
		return "owners";
	}

	@DeleteMapping("/{id}")
	public String deleteOwner(@PathVariable("id") int id, Model model) {
		ownerService.delete(id);
		return getOwners(model);
	}

	@GetMapping("/{id}/edit")
	String editOwner(@PathVariable int id, Model model) {
		Owner owner = ownerService.get(id);
		model.addAttribute("owner", owner);
		return "editOwner";
	}

	@GetMapping("/add")
	public String getAddOwnerPage(Model model) {
		return "addOwner";
	}

	@GetMapping("{id}")
	String getOwner(@PathVariable int id, Model model) {
		Owner owner = ownerService.get(id);
		model.addAttribute("owner", owner);
		return "owner";
	}

	@GetMapping
	String getOwners(Model model) {
		Iterable<Owner> owners = ownerService.getAll();
		List<Owner> ownersList = new ArrayList<>();
		for (Owner owner : owners) {
			ownersList.add(owner);
		}
		ownersList.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("owners", ownersList);
		return "owners";
	}

	@PostMapping("/edit")
	String updateOwner(@RequestParam(name = "id", required = true) int id,
			@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "phone", required = true) String phone, Model model) throws PersonException {
		Owner owner = ownerService.get(id);
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setEmail(email);
		owner.setPhone(phone);
		Owner savedOwner = ownerService.save(owner);
		if (savedOwner == null) {
			throw new PersonException("Saved owner not found.");
		}
		return getOwners(model);
	}

}
