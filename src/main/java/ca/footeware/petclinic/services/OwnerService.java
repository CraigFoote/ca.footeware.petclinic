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

	public void delete(int id) {
		repository.deleteById(id);
	}

	public List<Owner> findByFirstNameAndLastName(String firstName, String lastName) {
		return repository.findByFirstNameAndLastName(firstName, lastName);
	}

	public Owner get(int id) {
		return repository.findById(id).get();
	}

	public Iterable<Owner> getAll() {
		return repository.findAll();
	}

	public Owner save(Owner owner) {
		return repository.save(owner);
	}

}
