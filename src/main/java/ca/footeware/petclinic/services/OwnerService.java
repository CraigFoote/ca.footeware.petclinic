/**
 * 
 */
package ca.footeware.petclinic.services;

import java.util.List;
import java.util.Set;

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

	public void deleteOwner(Owner owner) {
		repository.delete(owner);
	}

	public Owner getById(String id) {
		return repository.getById(id);
	}

	public List<Owner> getByLastName(String lastName) {
		return repository.getByLastName(lastName);
	}

	public Owner saveOwner(Owner owner) {
		return repository.save(owner);
	}

	public Owner updateOwner(Owner owner) {
		return repository.save(owner);
	}

	public List<Owner> getOwners() {
		return repository.findAll();
	}

}
