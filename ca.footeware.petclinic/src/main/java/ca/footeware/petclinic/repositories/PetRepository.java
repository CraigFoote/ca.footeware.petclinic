
package ca.footeware.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Pet;

public interface PetRepository extends MongoRepository<Pet, String> {

	public Pet getById(String id);
}