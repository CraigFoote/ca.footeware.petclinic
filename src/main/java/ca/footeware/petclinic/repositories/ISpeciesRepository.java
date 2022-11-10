/**
 *
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.footeware.petclinic.models.Species;

/**
 * @author Footeware.ca
 *
 */
public interface ISpeciesRepository extends CrudRepository<Species, Integer> {
}
