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
	public String createBooking(
	    @RequestParam(name = "petId", required = true) String petId, 
	    @RequestParam(name = "vetId", required = true) String vetId,
		@RequestParam(name = "procedureId", required = true) String procedureId, 
		@RequestParam(name = "date", required = true) String date, 
		Model model)
	{
	    Pet pet = petService.get(petId);
	    Vet vet = vetService.get(vetId);
	    Procedure procedure = procedureService.get(procedureId);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); 
	    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
	    Booking booking = new Booking(pet, vet, procedure, dateTime);
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
		List<Booking> bookings = bookingService.getAll();
		// TODO Collections.reverseOrder()
		bookings.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
		model.addAttribute("bookings", bookings);
		return "bookings";
	}

	@GetMapping("/{id}/edit")
	String editBooking(@PathVariable String id, Model model) {
		Booking booking = bookingService.get(id);
		List<Pet> pets = petService.getAll();
		List<Vet> vets = vetService.getAll();
		List<Procedure> procedures = procedureService.getAll();
		model.addAttribute("booking", booking);
		model.addAttribute("pets", pets);
		model.addAttribute("vets", vets);
		model.addAttribute("procedures", procedures);
		return "editBooking";
	}

	@PostMapping("/edit")
	String updateBooking(
	    @RequestParam(name = "id", required = true) String id, 
	    @RequestParam(name = "petId", required = true) String petId,
		@RequestParam(name = "vetId", required = true) String vetId, 
		@RequestParam(name = "procedureId", required = true) String procedureId,
		@RequestParam(name = "date", required = true) String date,
		Model model) 
	{
		Booking booking = bookingService.get(id);
		booking.setPet(petService.get(petId));
		booking.setVet(vetService.get(vetId));
		booking.setProcedure(procedureService.get(procedureId));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); 
	    LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		booking.setDate(dateTime);
		Booking savedBooking = bookingService.save(booking);
		if(savedBooking == null){
		    throw new BookingException("Saved booking not found.");
		}
		return getBookings(model);
	}
