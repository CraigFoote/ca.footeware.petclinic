/**
 * 
 */
package ca.footeware.petclinic.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
import ca.footeware.petclinic.services.SpeciesService;

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

	@Autowired
	private SpeciesService speciesService;

	@PostMapping
	String searchByLastName(@RequestParam("lastName") String lastName, Model model) {
		model.addAttribute("lastName", lastName);
		List<Owner> ownersByLastName = ownerService.getByLastName(lastName);
		ownersByLastName.sort((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName()));
		model.addAttribute("owners", ownersByLastName);
		return "owners";
	}

	@GetMapping("/add")
	String getAddOwnerPage(Model model) {
		Map<Species, List<Pet>> petMap = petService.getOwnerlessPets();
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
	String getOwner(@PathVariable String id, Model model) {
		Owner owner = ownerService.getById(id);
		List<String> petIds = owner.getPets();
		List<Pet> pets = new ArrayList<>();
		for (String petId : petIds) {
			Pet pet = petService.getPet(petId);
			pets.add(pet);
		}
		pets.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("owner", owner);
		model.addAttribute("pets", pets);
		return "owner";
	}

	@GetMapping
	String getOwners(Model model) {
		List<Owner> owners = ownerService.getOwners();
		owners.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("owners", owners);
		return "owners";
	}

	@GetMapping("/{id}/edit")
	String editOwner(@PathVariable String id, Model model) {
		Owner owner = ownerService.getById(id);
		model.addAttribute("owner", owner);
		// Create a map of species to map of pets to a boolean indicating being owned.
		List<Species> allSpecies = speciesService.getAllSpecies();
		allSpecies.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		List<Pet> pets = petService.getPets();
		pets.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		SortedMap<Species, SortedMap<Pet, Boolean>> petOwnerMap = new TreeMap<>(
				(o1, o2) -> o1.getName().compareTo(o2.getName()));
		// set species as key and map of pets to boolean owned as value
		for (Species species : allSpecies) {
			SortedMap<Pet, Boolean> petPownedMap = new TreeMap<>((o1, o2) -> o1.getName().compareTo(o2.getName()));
			for (Pet pet : pets) {
				if (pet.getSpeciesId().equals(species.getId())) {
					boolean hasOwner = pet.getOwnerId() != null;
					boolean ownedByCurrentOwner = (hasOwner && pet.getOwnerId().equals(owner.getId())) ? true : false;
					// add if ownerless or owned by current owner; ignore if owned by someone else
					if (!hasOwner || ownedByCurrentOwner) {
						petPownedMap.put(pet, ownedByCurrentOwner);
					}
				}
			}
			petOwnerMap.put(species, petPownedMap);
		}
		model.addAttribute("petMap", petOwnerMap);
		return "editOwner";
	}

	@PostMapping("/edit")
	String updateOwner(@RequestParam("id") String id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam(name = "petIds", required = false) List<String> petIds,
			Model model) {
		Owner owner = ownerService.getById(id);
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setEmail(email);
		owner.setPhone(phone);
		owner.setPets(petIds);
		ownerService.saveOwner(owner);
		if (petIds != null) {
			List<Pet> allPets = petService.getPets();
			for (Pet pet : allPets) {
				if (petIds.contains(pet.getId())) {
					pet.setOwnerId(id);
				} else {
					pet.setOwnerId(null);
				}
			}
		}
		return getOwners(model);
	}

}
