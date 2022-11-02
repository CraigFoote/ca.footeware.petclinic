/**
 *
 */
package ca.footeware.petclinic.repositories;

import java.util.UUID;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import ca.footeware.petclinic.models.Vet;

/**
 * @author Footeware.ca
 *
 */
public interface IVetRepository extends CouchbaseRepository<Vet, UUID> {
}
