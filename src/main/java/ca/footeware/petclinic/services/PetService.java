package ca.footeware.petclinic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

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
	public Map<Species, List<Pet>> getOwnerlessPets() {
		List<Pet> pets = getPets();
		// get all species
		List<Species> speciesList = new ArrayList<>();
		for (Pet pet : pets) {
			String speciesId = pet.getSpeciesId();
			Species species = speciesService.getSpecies(speciesId);
			speciesList.add(species);
		}

		// Iterate over species which will become keys in map. Sort map by keys.
		SortedMap<Species, List<Pet>> petMap = new TreeMap<Species, List<Pet>>(
				(o1, o2) -> o1.getName().compareTo(o2.getName()));
		for (Species species : speciesList) {
			// for each species, create a list of pets
			List<Pet> petsOfSpecies = new ArrayList<>();
			for (Pet pet : pets) {
				// ignore owned pets
				if (pet.getOwnerId() == null) {
					// if it's the current species add it to a set
					if (pet.getSpeciesId().equals(species.getId())) {
						petsOfSpecies.add(pet);
					}
				}
			}
			// sort list of pets by name
			petsOfSpecies.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
			// put species and its pets in map
			petMap.put(species, petsOfSpecies);
			// sort map by keys
		}
		return petMap;
	}

}
