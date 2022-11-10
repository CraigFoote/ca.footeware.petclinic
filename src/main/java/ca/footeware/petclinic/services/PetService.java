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

	public void delete(int id) {
		repository.deleteById(id);
	}

	public Pet get(int id) {
		return repository.findById(id).get();
	}

	public Iterable<Pet> getAll() {
		return repository.findAll();
	}

	public Pet save(Pet pet) {
		return repository.save(pet);
	}

	public List<Pet> getByName(String name) {
		return repository.findAllByNameIgnoreCase(name);
	}

}
