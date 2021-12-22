/**
 *
 */
package ca.footeware.petclinic.services;

import java.util.List;

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

	public Booking get(String id) {
		return repository.findById(id).get();
	}

	public Booking save(Booking booking) {
		return repository.save(booking);
	}

	public List<Booking> getAll() {
		return repository.findAll();
	}

	public void delete(String id) {
		repository.deleteById(id);
	}

}
