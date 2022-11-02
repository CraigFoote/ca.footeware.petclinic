/**
 *
 */
package ca.footeware.petclinic.services;

import java.util.List;
import java.util.UUID;

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

	public List<Species> getAll() {
		return repository.findAll();
	}

	public Species get(UUID id) {
		return repository.findById(id).get();
	}

	public Species save(Species species) {
		Species saved = repository.save(species);
		return saved;
	}

	public void delete(UUID id) {
		repository.deleteById(id);
	}

}
