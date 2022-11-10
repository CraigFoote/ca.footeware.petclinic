/**
 *
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import ca.footeware.petclinic.models.Booking;

/**
 * @author Footeware.ca
 */
public interface IBookingRepository extends CrudRepository<Booking, Integer> {
}
