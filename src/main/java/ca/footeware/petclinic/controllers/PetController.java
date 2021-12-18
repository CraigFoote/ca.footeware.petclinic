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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.exceptions.PetException;
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
@RequestMapping("/pets")
public class PetController {

	@Autowired
	private PetService petService;

	@Autowired
	private OwnerService ownerService;

	@Autowired
	private SpeciesService speciesService;

	@PostMapping("/search")
	String searchByName(
	    @RequestParam(name = "name", required = true) String name, Model model) {
		model.addAttribute("name", name);
		List<Pet> pets = petService.getByName(name);
		pets.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("pets", pets);
		return "pets";
	}

	@DeleteMapping("/{id}")
	public String deletePet(@PathVariable("id") String id, Model model) {
		Pet pet = petService.get(id);
		petService.delete(pet);
		return getPets(model);
	}

	@GetMapping("/add")
	public String getAddPetPage(Model model) {
		List<Owner> owners = ownerService.getAll();
		owners.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("owners", owners);
		List<Species> allSpecies = speciesService.getAll();
		allSpecies.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("allSpecies", allSpecies);
		return "addPet";
	}

	@GetMapping("/{id}")
	public String getPet(@PathVariable("id") String id, Model model) {
		Pet pet = petService.get(id);
		model.addAttribute("pet", pet);
		return "pet";
	}

	@GetMapping("/{id}/edit")
	public String getEditPetPage(@PathVariable("id") String id, Model model) {
		Pet pet = petService.get(id);
		List<Owner> allOwners = ownerService.getAll();
		allOwners.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		List<Species> allSpecies = speciesService.getAll();
		allSpecies.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("pet", pet);
		model.addAttribute("allOwners", allOwners);
		model.addAttribute("allSpecies", allSpecies);
		return "editPet";
	}

	@GetMapping
	public String getPets(Model model) {
		List<Pet> pets = petService.getAll();
		pets.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("pets", pets);
		return "pets";
	}

	@PostMapping
	public String savePet(
	    @RequestParam(name = "name", required = true) String name,
	    @RequestParam(name = "speciesId", required = true) String speciesId,
		@RequestParam(name = "weight", required = true) int weight,
		@RequestParam(name = "gender", required = true) Pet.Gender gender,
		@RequestParam(name = "ownerId", required = true) String ownerId,
		Model model)
		    throws PetException
    {
		Species species = speciesService.get(speciesId);
		Owner owner = ownerService.get(ownerId);
		Pet pet = new Pet(name, species, weight, gender, owner);
		Pet savedPet = petService.save(pet);
		if (savedPet == null) {
			throw new PetException("Saved pet not found.");
		}
		return getPets(model);
	}

	@PostMapping("/edit")
	public String updatePet(
	    @RequestParam(name = "id", required = true) String id,
	    @RequestParam(name = "name", required = true) String name,
		@RequestParam(name = "speciesId", required = true) String speciesId,
		@RequestParam(name = "weight", required = true) int weight,
		@RequestParam(name = "gender", required = true) Pet.Gender gender,
		@RequestParam(name = "ownerId", required = true) String ownerId,
		Model model)
		    throws PetException
    {
		Pet pet = petService.get(id);
		pet.setName(name);
		pet.setSpecies(speciesService.get(speciesId));
		pet.setWeight(weight);
		pet.setGender(gender);
		pet.setOwner(ownerService.get(ownerId));
		Pet savedPet = petService.save(pet);
		if (savedPet == null) {
			throw new PetException("Saved pet not found.");
		}
		return getPets(model);
	}

}
