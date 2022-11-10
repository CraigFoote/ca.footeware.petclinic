/**
 *
 */
package ca.footeware.petclinic.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.footeware.petclinic.models.Owner;

/**
 * @author Footeware.ca
 */
public interface IOwnerRepository extends CrudRepository<Owner, Integer> {

	List<Owner> findByFirstNameAndLastName(String firstName, String lastName);
}
