/**
 *
 */
package ca.footeware.petclinic.repositories;

import java.util.UUID;

import org.springframework.data.couchbase.repository.CouchbaseRepository;

import ca.footeware.petclinic.models.Booking;

/**
 * @author Footeware.ca
 */
public interface IBookingRepository extends CouchbaseRepository<Booking, UUID> {
}
