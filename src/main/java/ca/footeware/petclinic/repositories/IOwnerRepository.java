/**
 *
 */
package ca.footeware.petclinic.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import ca.footeware.petclinic.models.Owner;

/**
 * @author Footeware.ca
 */
public interface IOwnerRepository extends CouchbaseRepository<Owner, UUID> {

	List<Owner> findByFirstNameAndLastName(String firstName, String lastName);
}
