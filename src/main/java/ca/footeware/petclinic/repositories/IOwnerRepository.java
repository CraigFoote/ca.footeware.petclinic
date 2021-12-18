/**
 *
 */
package ca.footeware.petclinic.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Owner;

/**
 * @author Footeware.ca
 *
 */
public interface IOwnerRepository extends MongoRepository<Owner, String> {

	List<Owner> findByFirstNameAndLastName(String firstName, String lastName);
}
