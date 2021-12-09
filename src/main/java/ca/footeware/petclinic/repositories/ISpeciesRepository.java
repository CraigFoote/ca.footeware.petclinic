/**
 * 
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Species;

/**
 * @author Footeware.ca
 *
 */
public interface ISpeciesRepository extends MongoRepository<Species, String> {
}
