/**
 * 
 */
package ca.footeware.petclinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.footeware.petclinic.models.Species;
import ca.footeware.petclinic.repositories.ISpeciesRepository;

/**
 * @author Footeware.ca
 *
 */
@Service
public class SpeciesService {

	@Autowired
	private ISpeciesRepository repository;

	public List<Species> getAllSpecies() {
		return repository.findAll();
	}

	public Species getSpecies(String id) {
		return repository.findById(id).get();
	}

	public void saveSpecies(Species species) {
		repository.save(species);
	}

}
