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
public class DoctorService {

	@Autowired
	private IVetRepository repository;

	public Vet get(int id) {
		return repository.findById(id).get();
	}

	public Vet save(Vet vet) {
		return repository.save(vet);
	}

	public Iterable<Vet> getAll() {
		return repository.findAll();
	}

}
