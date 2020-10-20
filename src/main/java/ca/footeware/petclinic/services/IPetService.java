package ca.footeware.petclinic.services;

import java.util.List;

import ca.footeware.petclinic.models.Pet;

public interface IPetService {
	
	public List<Pet> getPets();
	
	public Pet savePet(Pet pet);
	
	public void deletePet(Pet pet);
	
	public Pet getPet(String id);
	
	public Pet updatePet(Pet pet);
}
