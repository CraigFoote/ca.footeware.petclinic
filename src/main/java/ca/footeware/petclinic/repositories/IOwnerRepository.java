/**
 * 
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Person;

/**
 * @author Footeware.ca
 *
 */
public interface IOwnerRepository extends MongoRepository<Person, String> {
}
