/**
 * 
 */
package ca.footeware.petclinic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.exceptions.PersonException;
import ca.footeware.petclinic.models.Vet;
import ca.footeware.petclinic.models.Person;
import ca.footeware.petclinic.services.BookingService;

/**
 * @author Footeware.ca
 *
 */
@Controller
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping("/add")
	public String getAddBookingPage(Model model) {
		return "addBooking";
	}

	@PostMapping("/add")
	public String createBooking(@RequestParam("petId") String petId, @RequestParam("doctorId") String vetId,
			@RequestParam("procedureId") String procedureId, @RequestParam("date") String date, Model model)
	{
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); 
	    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
	    Booking booking = new Booking(petId, vetId, procedureId, dateTime);
	    Booking savedBooking = bookingService.save(booking);
		if (savedBooking == null) {
			throw new BookingException("Saved booking not found.");
		}
		return getBookings(model);
	}

	@GetMapping("{id}")
	String getBooking(@PathVariable String id, Model model) {
		Booking booking = bookingService.get(id);
		model.addAttribute("booking", booking);
		return "booking";
	}

	@GetMapping
	String getBookings(Model model) {
		List<Booking> rawBookings = bookingService.getAll();
		bookings.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
		List<BookingDTO> bookings = new ArrayList<>();
		for(Booking booking : bookings){
		    Pet pet = petService.get(booking.getPetId());
		    Vet vet = vetService.get(booking.getVetId());
		    Procedure procedure = procedureService.get(booking.getProcedureId());
		    BookingDTO bookingDTO = new BookingDTO(booking, pet, vet, procedure);
		}
		model.addAttribute("bookings", bookings);
		return "bookings";
	}

	@GetMapping("/{id}/edit")
	String editBooking(@PathVariable String id, Model model) {
		Booking booking = bookingService.get(id);
		model.addAttribute("booking", booking);
		return "editBooking";
	}

	@PostMapping("/edit")
	String updateBooking(@RequestParam("id") String id, @RequestParam("petId") String petId,
			@RequestParam("vetId") String doctorId, @RequestParam("procedureId") String procedureId,
			@RequestParam("date") String date,
			Model model) {
		Booking booking = bookingService.get(id);
		booking.setPetId(petId);
		booking.setVetId(vetId);
		booking.setEmail(email);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); 
	    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		booking.setDate(dateTime);
		bookingService.save(booking);
		return getBookings(model);
	}

}
