/**
 *
 */
package ca.footeware.petclinic.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.exceptions.BookingException;
import ca.footeware.petclinic.models.Booking;
import ca.footeware.petclinic.models.Pet;
import ca.footeware.petclinic.models.Procedure;
import ca.footeware.petclinic.models.Vet;
import ca.footeware.petclinic.services.BookingService;
import ca.footeware.petclinic.services.PetService;
import ca.footeware.petclinic.services.ProcedureService;
import ca.footeware.petclinic.services.VetService;

/**
 * @author Footeware.ca
 *
 */
@Controller
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private PetService petService;

	@Autowired
	private ProcedureService procedureService;

	@Autowired
	private VetService vetService;

	@GetMapping("/add")
	public String getAddBookingPage(Model model) {
		Iterable<Pet> pets = petService.getAll();
		Iterable<Vet> vets = vetService.getAll();
		Iterable<Procedure> procedures = procedureService.getAll();
		model.addAttribute("pets", pets);
		model.addAttribute("vets", vets);
		model.addAttribute("procedures", procedures);
		return "addBooking";
	}

	@PostMapping
	public String createBooking(@RequestParam(name = "petId", required = true) UUID petId,
			@RequestParam(name = "vetId", required = true) UUID vetId,
			@RequestParam(name = "procedureId", required = true) UUID procedureId,
			@RequestParam(name = "date", required = true) String date, Model model) throws BookingException {
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
	String getBooking(@PathVariable UUID id, Model model) {
		Booking booking = bookingService.get(id);
		model.addAttribute("booking", booking);
		return "booking";
	}

	@GetMapping
	String getBookings(Model model) {
		Iterable<Booking> bookings = bookingService.getAll();
		Comparator<Booking> comparator = new Comparator<>() {
			@Override
			public int compare(Booking o1, Booking o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		};
		List<Booking> bookingsList = new ArrayList<>();
		for (Booking booking : bookings) {
			bookingsList.add(booking);
		}
		Collections.sort(bookingsList, Collections.reverseOrder(comparator));
		model.addAttribute("bookings", bookingsList);
		return "bookings";
	}

	@GetMapping("/{id}/edit")
	String editBooking(@PathVariable UUID id, Model model) {
		Booking booking = bookingService.get(id);
		Iterable<Pet> pets = petService.getAll();
		Iterable<Vet> vets = vetService.getAll();
		Iterable<Procedure> procedures = procedureService.getAll();
		model.addAttribute("booking", booking);
		model.addAttribute("pets", pets);
		model.addAttribute("vets", vets);
		model.addAttribute("procedures", procedures);
		return "editBooking";
	}

	@PostMapping("/edit")
	String updateBooking(@RequestParam(name = "id", required = true) UUID id,
			@RequestParam(name = "petId", required = true) UUID petId,
			@RequestParam(name = "vetId", required = true) UUID vetId,
			@RequestParam(name = "procedureId", required = true) UUID procedureId,
			@RequestParam(name = "date", required = true) String date, Model model) throws BookingException {
		Booking booking = bookingService.get(id);
		booking.setPet(petService.get(petId));
		booking.setVet(vetService.get(vetId));
		booking.setProcedure(procedureService.get(procedureId));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		booking.setDate(dateTime);
		Booking savedBooking = bookingService.save(booking);
		if (savedBooking == null) {
			throw new BookingException("Saved booking not found.");
		}
		return getBookings(model);
	}

	@DeleteMapping("/{id}")
	public String deleteBooking(@PathVariable("id") UUID id, Model model) {
		bookingService.delete(id);
		return getBookings(model);
	}
}
