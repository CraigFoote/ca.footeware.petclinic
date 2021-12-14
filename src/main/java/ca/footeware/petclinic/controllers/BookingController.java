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

import ca.footeware.petclinic.exceptions.LostPersonException;
import ca.footeware.petclinic.models.Doctor;
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
	public String createBooking(@RequestParam("petId") String petId, @RequestParam("doctorId") String doctorId,
			@RequestParam("procedureId") String procedureId, @RequestParam("date") String date, Model model)
	{
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); 
	    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
	    Booking booking = new Booking(petId, doctorId, procedureId, dateTime);
	    Booking savedBooking = bookingService.save(booking);
		if (savedBooking == null) {
			throw new BookingException("Saved booking not found.");
		}
		model.addAttribute("bookings", bookingService.getBookings());
		return "bookings";
	}

	@GetMapping("{id}")
	String getBooking(@PathVariable String id, Model model) {
		Booking booking = bookingService.getById(id);
		model.addAttribute("booking", booking);
		return "booking";
	}

	@GetMapping
	String getBookings(Model model) {
		List<Booking> bookings = bookingService.getAll();
		bookings.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
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
			@RequestParam("doctorId") String doctorId, @RequestParam("procedureId") String procedureId,
			@RequestParam("date") String date,
			Model model) {
		Booking booking = bookingService.get(id);
		booking.setPetId(petId);
		booking.setDoctorId(doctorId);
		booking.setEmail(email);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); 
	    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		booking.setDate(dateTime);
		bookingService.save(booking);
		return getBookings(model);
	}

}
