package ca.footeware.petclinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.footeware.petclinic.models.Pet;
import ca.footeware.petclinic.repositories.PetRepository;

@Service
public class PetService implements IPetService {

	@Autowired
	private PetRepository repository;

	public PetService() {
	}

	@Override
	public List<Pet> getPets() {
		return repository.findAll();
	}

	@Override
	public Pet savePet(Pet pet) {
		return repository.save(pet);
	}

	@Override
	public void deletePet(Pet pet) {
		repository.delete(pet);
	}

	@Override
	public Pet getPet(String id) {
		return repository.getById(id);
	}

	@Override
	public Pet updatePet(Pet pet) {
		return repository.insert(pet);
	}

}
