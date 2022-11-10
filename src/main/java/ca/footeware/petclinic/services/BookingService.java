/**
 *
 */
package ca.footeware.petclinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.footeware.petclinic.models.Booking;
import ca.footeware.petclinic.repositories.IBookingRepository;

/**
 * @author Footeware.ca
 *
 */
@Service
public class BookingService {

	@Autowired
	private IBookingRepository repository;

	public Booking get(int id) {
		return repository.findById(id).get();
	}

	public Booking save(Booking booking) {
		return repository.save(booking);
	}

	public Iterable<Booking> getAll() {
		return repository.findAll();
	}

	public void delete(int id) {
		repository.deleteById(id);
	}

}
