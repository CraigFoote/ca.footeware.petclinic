/**
 *
 */
package ca.footeware.petclinic.services;

import java.util.List;

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

	public Vet get(String vetId) {
		return repository.findById(vetId).get();
	}

	public List<Vet> getAll() {
		return repository.findAll();
	}

	public Vet save(Vet vet) {
		return repository.save(vet);
	}

	public void delete(String id) {
		repository.deleteById(id);
	}

}
