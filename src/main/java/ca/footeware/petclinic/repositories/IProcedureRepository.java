/**
 *
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.footeware.petclinic.models.Procedure;

/**
 * @author Footeware.ca
 *
 */
public interface IProcedureRepository extends CrudRepository<Procedure, Integer> {
}
