package ca.footeware.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Pet;

public interface IPetRepository extends MongoRepository<Pet, String> {

	Pet getById(String id);

}