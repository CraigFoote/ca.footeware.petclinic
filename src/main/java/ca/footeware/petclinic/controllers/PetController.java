/**
 * 
 */
package ca.footeware.petclinic.controllers;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.exceptions.LostPetException;
import ca.footeware.petclinic.models.Owner;
import ca.footeware.petclinic.models.Person;
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
@RequestMapping("/pets")
public class PetController {

	@Autowired
	private PetService petService;

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private SpeciesService speciesService;

	@PostMapping("/search")
	String searchByName(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		List<Pet> petsByName = petService.getByName(name);
		petsByName.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		SortedMap<Pet, Species> petSpeciesMap = new TreeMap<>((o1, o2) -> o1.getName().compareTo(o2.getName()));
		for (Pet pet : petsByName) {
			String speciesId = pet.getSpeciesId();
			Species species = speciesService.getSpecies(speciesId);
			petSpeciesMap.put(pet, species);
		}
		model.addAttribute("petSpeciesMap", petSpeciesMap);
		return "pets";
	}

	@DeleteMapping("/{id}")
	public String deletePet(@PathVariable("id") String id, Model model) {
		Pet pet = petService.getPet(id);
		petService.deletePet(pet);
		return getPets(model);
	}

	@GetMapping("/add")
	public String getAddPetPage(Model model) {
		List<Owner> owners = ownerService.getOwners();
		owners.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("owners", owners);
		List<Species> allSpecies = speciesService.getAllSpecies();
		allSpecies.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("species", allSpecies);
		return "addPet";
	}

	@GetMapping("/{id}")
	public String getPet(@PathVariable("id") String id, Model model) {
		Pet pet = petService.getPet(id);
		Species species = speciesService.getSpecies(pet.getSpeciesId());
		String ownerId = pet.getOwnerId();
		if (ownerId != null) {
			Person owner = ownerService.getById(ownerId);
			model.addAttribute("owner", owner);
		}
		model.addAttribute("pet", pet);
		model.addAttribute("species", species);
		return "pet";
	}

	@GetMapping("/{id}/edit")
	public String getEditPetPage(@PathVariable("id") String id, Model model) {
		Pet pet = petService.getPet(id);
		Person owner = null;
		if (pet.getOwnerId() != null) {
			owner = ownerService.getById(pet.getOwnerId());
		}
		List<Owner> allOwners = ownerService.getOwners();
		allOwners.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		Species species = speciesService.getSpecies(pet.getSpeciesId());
		List<Species> allSpecies = speciesService.getAllSpecies();
		allSpecies.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("pet", pet);
		if (owner != null) {
			model.addAttribute("petOwner", owner);
		}
		model.addAttribute("allOwners", allOwners);
		model.addAttribute("species", species);
		model.addAttribute("allSpecies", allSpecies);
		return "editPet";
	}

	@GetMapping
	public String getPets(Model model) {
		List<Pet> pets = petService.getPets();
		pets.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		SortedMap<Pet, Species> petSpeciesMap = new TreeMap<>((o1, o2) -> o1.getName().compareTo(o2.getName()));
		for (Pet pet : pets) {
			String speciesId = pet.getSpeciesId();
			Species species = speciesService.getSpecies(speciesId);
			petSpeciesMap.put(pet, species);
		}
		model.addAttribute("petSpeciesMap", petSpeciesMap);
		return "pets";
	}

	@PostMapping
	public String savePet(@RequestParam("name") String name, @RequestParam("species") Species species,
			@RequestParam(name = "weight", defaultValue = "-1") int weight, @RequestParam("gender") Pet.Gender gender,
			@RequestParam(name = "license", required = false) String license,
			@RequestParam(name = "ownerId", required = false) String ownerId, Model model) throws LostPetException {
		Pet pet = new Pet(name, species.getId());
		pet.setWeight(weight);
		pet.setGender(gender);
		pet.setOwnerId(ownerId);
		Pet newPet = petService.savePet(pet);
		if (newPet == null) {
			throw new LostPetException();
		}
		return getPets(model);
	}

	@PostMapping("/edit")
	public String updatePet(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("species") Species species, @RequestParam("weight") int weight,
			@RequestParam("gender") Pet.Gender gender, @RequestParam(name = "ownerId", required = false) String ownerId,
			@RequestParam(name = "license", required = false) String license, Model model) throws LostPetException {
		Pet pet = petService.getPet(id);
		pet.setId(id);
		pet.setName(name);
		pet.setSpeciesId(species.getId());
		pet.setWeight(weight);
		pet.setGender(gender);
		pet.setOwnerId(ownerId);
		Pet savedPet = petService.savePet(pet);
		if (savedPet == null) {
			throw new LostPetException();
		}
		return getPets(model);
	}

}
