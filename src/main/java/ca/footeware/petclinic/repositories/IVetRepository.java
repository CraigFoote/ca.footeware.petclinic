/**
 * 
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Vet;

/**
 * @author Footeware.ca
 *
 */
public interface IVetRepository extends MongoRepository<Vet, String> {
}
