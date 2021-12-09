/**
 * 
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Procedure;

/**
 * @author Footeware.ca
 *
 */
public interface IProcedureRepository extends MongoRepository<Procedure, String> {
}
