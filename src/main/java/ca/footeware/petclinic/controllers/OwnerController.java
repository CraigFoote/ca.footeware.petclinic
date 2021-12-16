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
	public String createOwner(
	    @RequestParam(name = "firstName", required = true) String firstName, 
	    @RequestParam(name = "lastName", required = true) String lastName,
		@RequestParam(name = "email", required = true) String email, 
		@RequestParam(name = "phone", required = true) String phone, 
		Model model)
			throws LostPersonException 
	{
		Owner owner = new Owner(firstName, lastName, email, phone);
		Owner savedOwner = ownerService.save(owner);
		if (savedOwner == null) {
			throw new PersonException("Saved owner not found.");
		}
		model.addAttribute("owners", ownerService.getAll());
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
		List<Owner> owners = ownerService.getAll();
		owners.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("owners", owners);
		return "owners";
	}

	@GetMapping("/{id}/edit")
	String editOwner(@PathVariable String id, Model model) {
		Person owner = ownerService.get(id);
		model.addAttribute("owner", owner);
		return "editOwner";
	}

	@PostMapping("/edit")
	String updateOwner(
	    @RequestParam(name = "id", required = true) String id, 
	    @RequestParam(name = "firstName", required = true) String firstName,
		@RequestParam(name = "lastName", required = true) String lastName, 
		@RequestParam(name = "email", required = true) String email,
		@-RequestParam(name = "phone", required = true) String phone,
		Model model) 
	{
		Owner owner = ownerService.get(id);
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setEmail(email);
		owner.setPhone(phone);
		Owner savedOwner = ownerService.save(owner);
		if (savedOwner == null){
		    throw new PersonException("Saved owner not found.");
		}
		return getOwners(model);
	}

}
