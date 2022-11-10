/**
 *
 */
package ca.footeware.petclinic.models.dto;

import ca.footeware.petclinic.models.Booking;
import ca.footeware.petclinic.models.Pet;
import ca.footeware.petclinic.models.Procedure;
import ca.footeware.petclinic.models.Vet;

/**
 * @author craig
 *
 */
public class BookingDTO {

	private Booking booking;
	private Pet pet;
	private Vet vet;
	private Procedure procedure;

	/**
	 * @param booking
	 * @param pet
	 * @param vetId
	 * @param procedureId
	 */
	public BookingDTO(Booking booking, Pet pet, Vet vet, Procedure procedure) {
		this.booking = booking;
		this.pet = pet;
		this.vet = vet;
		this.procedure = procedure;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Vet getVet() {
		return vet;
	}

	public void setVet(Vet vet) {
		this.vet = vet;
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

}
