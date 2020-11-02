/**
 * 
 */
package ca.footeware.petclinic.repositories;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Owner;

/**
 * @author Footeware.ca
 *
 */
public interface IOwnerRepository extends MongoRepository<Owner, String> {

	Owner getById(String id);

	Set<Owner> getByLastName(String lastName);

}
