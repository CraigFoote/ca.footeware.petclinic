/**
 *
 */
package ca.footeware.petclinic.services;

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

	public Iterable<Species> getAll() {
		return repository.findAll();
	}

	public Species get(int id) {
		return repository.findById(id).get();
	}

	public Species save(Species species) {
		Species saved = repository.save(species);
		return saved;
	}

	public void delete(int id) {
		repository.deleteById(id);
	}

}
