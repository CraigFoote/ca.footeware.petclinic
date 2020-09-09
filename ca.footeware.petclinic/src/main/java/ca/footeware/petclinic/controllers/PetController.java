/**
 * 
 */
package ca.footeware.petclinic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.exceptions.LostPetException;
import ca.footeware.petclinic.models.Cat;
import ca.footeware.petclinic.models.Dog;
import ca.footeware.petclinic.models.Pet;
import ca.footeware.petclinic.services.IPetService;
import ca.footeware.petclinic.services.NextSequenceService;

/**
 * @author Footeware.ca
 *
 */
@Controller
public class PetController {

	@Autowired
	private IPetService petService;
	@Autowired
	private NextSequenceService sequenceService;

	/**
	 * 
	 */
	public PetController() {
	}

	@GetMapping("/pets")
	public String getPets(Model model) {
		List<Pet> list = petService.getPets();
		model.addAttribute("pets", list);
		return "pets";
	}

	@PostMapping("/pets")
	public String savePet(@RequestParam("name") String name, @RequestParam("species") String species,
			@RequestParam("breed") String breed, @RequestParam("weight") int weight,
			@RequestParam("gender") Pet.Gender gender,
			@RequestParam(value = "fixed", defaultValue = "false") boolean fixed,
			@RequestParam("ownerName") String ownerName, @RequestParam("ownerPhone") String ownerPhone,
			@RequestParam("notes") String notes, Model model) {

		String nextSequence = sequenceService.getNextSequence("customSequences");
		Pet pet = switch (species) {
		case "dog" -> new Dog(nextSequence, name, species, breed, weight, gender, fixed, ownerName, ownerPhone, notes);
		case "cat" -> new Cat(nextSequence, name, species, breed, weight, gender, fixed, ownerName, ownerPhone, notes);
		default -> throw new IllegalArgumentException("Unexpected value: " + species);
		};

		petService.savePet(pet);
		return getPets(model);
	}

	@GetMapping("/pets/add")
	public String getAddAccountPage() {
		return "addPet";
	}

	@GetMapping("/pets/{id}")
	public String getPet(@PathVariable("id") String id, Model model) {
		Pet pet = petService.getPet(id);
		model.addAttribute("pet", pet);
		return "editPet";
	}

	@DeleteMapping("/pets/{id}")
	public String deletePet(@PathVariable("id") String id, Model model) {
		Pet pet = petService.getPet(id);
		petService.deletePet(pet);
		return getPets(model);
	}

	@PutMapping("/pets")
	public String updatePet(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("species") String species, @RequestParam("breed") String breed,
			@RequestParam("weight") int weight, @RequestParam("gender") Pet.Gender gender,
			@RequestParam(value = "fixed", defaultValue = "false") boolean fixed,
			@RequestParam("ownerName") String ownerName, @RequestParam("ownerPhone") String ownerPhone,
			@RequestParam("notes") String notes, Model model) throws LostPetException {
		Pet pet = petService.getPet(id);
		pet.setId(id);
		pet.setName(name);
		pet.setSpecies(species);
		pet.setBreed(breed);
		pet.setWeight(weight);
		pet.setGender(gender);
		pet.setFixed(fixed);
		pet.setOwnerName(ownerName);
		pet.setOwnerPhone(ownerPhone);
		pet.setNotes(notes);
		Pet savedPet = petService.savePet(pet);
		if (savedPet == null) {
			throw new LostPetException();
		}
		model.addAttribute("pets", petService.getPets());
		return "pets";
	}

}
