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
import ca.footeware.petclinic.models.Owner;
import ca.footeware.petclinic.models.Pet;
import ca.footeware.petclinic.models.Procedure;
import ca.footeware.petclinic.models.Species;
import ca.footeware.petclinic.models.Vet;
import ca.footeware.petclinic.models.dto.BookingDTO;
import ca.footeware.petclinic.models.dto.PetDTO;
import ca.footeware.petclinic.services.BookingService;
import ca.footeware.petclinic.services.OwnerService;
import ca.footeware.petclinic.services.PetService;
import ca.footeware.petclinic.services.ProcedureService;
import ca.footeware.petclinic.services.SpeciesService;
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

	@Autowired
	private SpeciesService speciesService;

	@Autowired
	private OwnerService ownerService;

	@GetMapping("/add")
	public String getAddBookingPage(Model model) {
		Iterable<Pet> pets = petService.getAll();
		List<PetDTO> petsList = new ArrayList<>();
		for (Pet pet : pets) {
			Species species = speciesService.get(pet.getSpeciesId());
			Owner owner = ownerService.get(pet.getOwnerId());
			petsList.add(new PetDTO(pet, species, owner));
		}
		petsList.sort((o1, o2) -> o1.getPet().getName().compareTo(o2.getPet().getName()));
		model.addAttribute("pets", petsList);

		Iterable<Vet> vets = vetService.getAll();
		List<Vet> vetsList = new ArrayList<>();
		for (Vet vet : vets) {
			vetsList.add(vet);
		}
		vetsList.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("vets", vetsList);

		Iterable<Procedure> procedures = procedureService.getAll();
		List<Procedure> proceduresList = new ArrayList<>();
		for (Procedure procedure : procedures) {
			proceduresList.add(procedure);
		}
		proceduresList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("procedures", proceduresList);

		return "addBooking";
	}

	@PostMapping
	public String createBooking(@RequestParam(name = "petId", required = true) int petId,
			@RequestParam(name = "vetId", required = true) int vetId,
			@RequestParam(name = "procedureId", required = true) int procedureId,
			@RequestParam(name = "date", required = true) String date, Model model) throws BookingException {
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
	String getBooking(@PathVariable int id, Model model) {
		Booking booking = bookingService.get(id);
		model.addAttribute("booking", booking);
		return "booking";
	}

	@GetMapping
	String getBookings(Model model) {
		Iterable<Booking> bookings = bookingService.getAll();
		Comparator<BookingDTO> comparator = (o1, o2) -> o1.getBooking().getDate().compareTo(o2.getBooking().getDate());
		List<BookingDTO> bookingsList = new ArrayList<>();
		for (Booking booking : bookings) {
			Pet pet = petService.get(booking.getPetId());
			Vet vet = vetService.get(booking.getVetId());
			Procedure procedure = procedureService.get(booking.getProcedureId());
			BookingDTO dto = new BookingDTO(booking, pet, vet, procedure);
			bookingsList.add(dto);
		}
		Collections.sort(bookingsList, Collections.reverseOrder(comparator));
		model.addAttribute("bookings", bookingsList);
		return "bookings";
	}

	@GetMapping("/{id}/edit")
	String editBooking(@PathVariable int id, Model model) {
		Booking booking = bookingService.get(id);
		Pet pet = petService.get(booking.getPetId());
		Vet vet = vetService.get(booking.getVetId());
		Procedure procedure = procedureService.get(booking.getProcedureId());
		model.addAttribute("bookingDTO", new BookingDTO(booking, pet, vet, procedure));

		List<PetDTO> petDTOs = new ArrayList<>();
		Iterable<Pet> pets = petService.getAll();
		for (Pet aPet : pets) {
			Species species = speciesService.get(aPet.getSpeciesId());
			Owner owner = ownerService.get(aPet.getOwnerId());
			petDTOs.add(new PetDTO(pet, species, owner));
		}
		model.addAttribute("petDTOs", petDTOs);

		Iterable<Vet> vets = vetService.getAll();
		List<Vet> vetsList = new ArrayList<>();
		for (Vet vet2 : vets) {
			vetsList.add(vet2);
		}
		vetsList.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("vets", vetsList);

		Iterable<Procedure> procedures = procedureService.getAll();
		List<Procedure> proceduresList = new ArrayList<>();
		for (Procedure procedure2 : procedures) {
			proceduresList.add(procedure2);
		}
		proceduresList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("procedures", proceduresList);

		return "editBooking";
	}

	@PostMapping("/edit")
	String updateBooking(@RequestParam(name = "id", required = true) int id,
			@RequestParam(name = "petId", required = true) int petId,
			@RequestParam(name = "vetId", required = true) int vetId,
			@RequestParam(name = "procedureId", required = true) int procedureId,
			@RequestParam(name = "date", required = true) String date, Model model) throws BookingException {
		Booking booking = bookingService.get(id);
		booking.setPetId(petId);
		booking.setVetId(vetId);
		booking.setProcedureId(procedureId);
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
	public String deleteBooking(@PathVariable("id") int id, Model model) {
		bookingService.delete(id);
		return getBookings(model);
	}
}
