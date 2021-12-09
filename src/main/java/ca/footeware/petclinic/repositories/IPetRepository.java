package ca.footeware.petclinic.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Pet;

public interface IPetRepository extends MongoRepository<Pet, String> {

	List<Pet> findAllByName(String name);

}