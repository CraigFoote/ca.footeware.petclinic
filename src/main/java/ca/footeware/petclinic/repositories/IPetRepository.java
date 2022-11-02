package ca.footeware.petclinic.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import ca.footeware.petclinic.models.Pet;

public interface IPetRepository extends CouchbaseRepository<Pet, UUID> {

	List<Pet> findAllByNameIgnoreCase(String name);

}