package ca.footeware.petclinic.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.footeware.petclinic.models.Pet;
import ca.footeware.petclinic.models.Species;
import ca.footeware.petclinic.repositories.IPetRepository;

@Service
public class PetService {

	@Autowired
	private IPetRepository repository;

	@Autowired
	private SpeciesService speciesService;

	public void deletePet(Pet pet) {
		repository.delete(pet);
	}

	public Pet getPet(String id) {
		return repository.getById(id);
	}

	public List<Pet> getPets() {
		return repository.findAll();
	}

	public Pet savePet(Pet pet) {
		return repository.save(pet);
	}

	public Pet updatePet(Pet pet) {
		return repository.insert(pet);
	}

	/**
	 * Get the set of pets without owner sorted by species.
	 * 
	 * @return {@link Map} of {@link Species} to {@link Set} of {@link Pet}.
	 */
	public Map<Species, Set<Pet>> getOwnerlessPets() {
		List<Pet> pets = getPets();
		// get all species
		Set<Species> speciesSet = new HashSet<>();
		for (Pet pet : pets) {
			String speciesId = pet.getSpeciesId();
			Species species = speciesService.getSpecies(speciesId);
			speciesSet.add(species);
		}
		Map<Species, Set<Pet>> petMap = new HashMap<>();
		for (Species species : speciesSet) {
			// for each species, create a set of pets
			Set<Pet> petsOfSpecies = new HashSet<>();
			for (Pet pet : pets) {
				// ignore owned pets
				if (pet.getOwnerId() == null) {
					// if it's the current species add it to a set
					if (pet.getSpeciesId().equals(species.getId())) {
						petsOfSpecies.add(pet);
					}
				}
			}
			// put species and its pets in map
			petMap.put(species, petsOfSpecies);
		}
		return petMap;
	}

}
