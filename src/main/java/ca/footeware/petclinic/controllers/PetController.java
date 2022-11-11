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

import ca.footeware.petclinic.exceptions.PetException;
import ca.footeware.petclinic.models.Gender;
import ca.footeware.petclinic.models.Owner;
import ca.footeware.petclinic.models.Pet;
import ca.footeware.petclinic.models.Species;
import ca.footeware.petclinic.models.dto.PetDTO;
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

	@PostMapping
	public String createPet(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "speciesId", required = true) int speciesId,
			@RequestParam(name = "weight", required = true) int weight,
			@RequestParam(name = "gender", required = true) Gender gender,
			@RequestParam(name = "ownerId", required = true) int ownerId, Model model) throws PetException {
		Pet pet = new Pet(name, weight, gender, speciesId, ownerId);
		Pet savedPet = petService.save(pet);
		if (savedPet == null) {
			throw new PetException("Saved pet not found.");
		}
		return getPets(model);
	}

	@DeleteMapping("/{id}")
	public String deletePet(@PathVariable("id") int id, Model model) {
		petService.delete(id);
		return getPets(model);
	}

	@GetMapping("/{id}")
	public String editPet(@PathVariable("id") int id, Model model) {
		Pet pet = petService.get(id);
		Iterable<Owner> allOwners = ownerService.getAll();
		List<Owner> allOwnersList = new ArrayList<>();
		for (Owner owner : allOwners) {
			allOwnersList.add(owner);
		}
		allOwnersList.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		Iterable<Species> allSpecies = speciesService.getAll();
		List<Species> allSpeciesList = new ArrayList<>();
		for (Species species : allSpecies) {
			allSpeciesList.add(species);
		}
		allSpeciesList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		Species species = speciesService.get(pet.getSpeciesId());
		Owner owner = ownerService.get(pet.getOwnerId());
		PetDTO dto = new PetDTO(pet, species, owner);
		model.addAttribute("dto", dto);
		model.addAttribute("allOwners", allOwnersList);
		model.addAttribute("allSpecies", allSpeciesList);
		return "editPet";
	}

	@GetMapping("/add")
	public String getAddPetPage(Model model) {
		Iterable<Owner> owners = ownerService.getAll();
		List<Owner> ownersList = new ArrayList<>();
		for (Owner owner : owners) {
			ownersList.add(owner);
		}
		ownersList.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("owners", ownersList);
		Iterable<Species> allSpecies = speciesService.getAll();
		List<Species> allSpeciesList = new ArrayList<>();
		for (Species species : allSpecies) {
			allSpeciesList.add(species);
		}
		allSpeciesList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("allSpecies", allSpecies);
		return "addPet";
	}

	@GetMapping
	public String getPets(Model model) {
		// FIXME, necessary to get accurate inventory
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterable<Pet> pets = petService.getAll();
		List<Pet> petsList = new ArrayList<>();
		for (Pet pet : pets) {
			petsList.add(pet);
		}
		petsList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

		// convert to Data Transfer Objects
		List<PetDTO> dtos = new ArrayList<>();
		for (Pet pet : petsList) {
			Species species = speciesService.get(pet.getSpeciesId());
			Owner owner = ownerService.get(pet.getOwnerId());
			PetDTO dto = new PetDTO(pet, species, owner);
			dtos.add(dto);
		}
		model.addAttribute("pets", dtos);
		return "pets";
	}

	@PostMapping("/search")
	String searchByName(@RequestParam(name = "name", required = true) String name, Model model) {
		model.addAttribute("name", name);
		List<Pet> pets = petService.getByName(name);
		pets.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		List<PetDTO> dtos = new ArrayList<>();
		for (Pet pet : pets) {
			Species species = speciesService.get(pet.getSpeciesId());
			Owner owner = ownerService.get(pet.getOwnerId());
			PetDTO dto = new PetDTO(pet, species, owner);
			dtos.add(dto);
		}
		model.addAttribute("pets", dtos);
		return "pets";
	}

	@PostMapping("/update")
	public String updatePet(@RequestParam(name = "id", required = true) int id,
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "speciesId", required = true) int speciesId,
			@RequestParam(name = "weight", required = true) int weight,
			@RequestParam(name = "gender", required = true) Gender gender,
			@RequestParam(name = "ownerId", required = true) int ownerId, Model model) throws PetException {
		Pet pet = petService.get(id);
		pet.setName(name);
		pet.setSpeciesId(speciesId);
		pet.setWeight(weight);
		pet.setGender(gender);
		pet.setOwnerId(ownerId);
		Pet savedPet = petService.save(pet);
		if (savedPet == null) {
			throw new PetException("Saved pet not found.");
		}
		return getPets(model);
	}

}
