/**
 *
 */
package ca.footeware.petclinic.repositories;

import java.util.UUID;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import ca.footeware.petclinic.models.Species;

/**
 * @author Footeware.ca
 *
 */
public interface ISpeciesRepository extends CouchbaseRepository<Species, UUID> {
}
