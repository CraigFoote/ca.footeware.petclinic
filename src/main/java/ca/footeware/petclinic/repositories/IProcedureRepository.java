/**
 *
 */
package ca.footeware.petclinic.repositories;

import java.util.UUID;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import ca.footeware.petclinic.models.Procedure;

/**
 * @author Footeware.ca
 *
 */
public interface IProcedureRepository extends CouchbaseRepository<Procedure, UUID> {
}
