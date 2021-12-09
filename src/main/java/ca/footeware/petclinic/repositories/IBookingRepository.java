/**
 * 
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Booking;

/**
 * @author Footeware.ca
 *
 */
public interface IBookingRepository extends MongoRepository<Booking, String> {
}
