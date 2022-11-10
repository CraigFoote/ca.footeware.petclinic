package ca.footeware.petclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.footeware.petclinic.models.Pet;

public interface IPetRepository extends CrudRepository<Pet, Integer> {

	List<Pet> findAllByNameIgnoreCase(String name);

}