/**
 *
 */
package ca.footeware.petclinic.services;

import java.util.List;
import java.util.UUID;

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

	public Vet get(UUID id) {
		return repository.findById(id).get();
	}

	public Vet save(Vet vet) {
		return repository.save(vet);
	}

	public List<Vet> getAll() {
		return repository.findAll();
	}

}
