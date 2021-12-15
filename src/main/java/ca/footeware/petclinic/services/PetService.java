package ca.footeware.petclinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.footeware.petclinic.models.Pet;
import ca.footeware.petclinic.repositories.IPetRepository;

@Service
public class PetService {

	@Autowired
	private IPetRepository repository;

	public void delete(Pet pet) {
		repository.delete(pet);
	}

	public Pet get(String id) {
		return repository.findById(id).get();
	}

	public List<Pet> getAll() {
		return repository.findAll();
	}

	public Pet save(Pet pet) {
		return repository.save(pet);
	}

	public List<Pet> getByName(String name) {
		return repository.findAllByName(name);
	}

}
