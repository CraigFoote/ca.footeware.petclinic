/**
 *
 */
package ca.footeware.petclinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.footeware.petclinic.models.Vet;
import ca.footeware.petclinic.repositories.IVetRepository;

/**
 * @author Footeware.ca
 *
 */
@Service
public class VetService {

	@Autowired
	private IVetRepository repository;

	public void delete(int id) {
		repository.deleteById(id);
	}

	public Vet get(int vetId) {
		return repository.findById(vetId).get();
	}

	public Iterable<Vet> getAll() {
		return repository.findAll();
	}

	public Vet save(Vet vet) {
		return repository.save(vet);
	}

}
