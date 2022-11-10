/**
 *
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.footeware.petclinic.models.Vet;

/**
 * @author Footeware.ca
 *
 */
public interface IVetRepository extends CrudRepository<Vet, Integer> {
}
