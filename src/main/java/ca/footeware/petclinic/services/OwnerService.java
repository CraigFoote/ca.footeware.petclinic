/**
 *
 */
package ca.footeware.petclinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.footeware.petclinic.models.Owner;
import ca.footeware.petclinic.repositories.IOwnerRepository;

/**
 * @author Footeware.ca
 *
 */
@Service
public class OwnerService {

	@Autowired
	private IOwnerRepository repository;

	public void delete(Owner owner) {
		repository.delete(owner);
	}

	public Owner get(String id) {
		return repository.findById(id).get();
	}

	public Owner save(Owner owner) {
		return repository.save(owner);
	}

	public List<Owner> getAll() {
		return repository.findAll();
	}

}
