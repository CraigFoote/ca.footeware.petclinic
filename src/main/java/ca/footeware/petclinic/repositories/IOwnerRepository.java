/**
 * 
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Owner;

/**
 * @author Footeware.ca
 *
 */
public interface IOwnerRepository extends MongoRepository<Owner, String> {
}
