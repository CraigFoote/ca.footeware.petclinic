/**
 * 
 */
package ca.footeware.petclinic.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

/**
 * @author Footeware.ca
 *
 */
public class Booking {

	@Id
	private String id = UUID.randomUUID().toString();
	private Pet pet;
	private Vet vet;
	private Procedure procedure;
	private LocalDateTime date;

	/**
	 * Constructor.
	 * 
	 * @param pet    {@link Pet}
	 * @param vet {@link Vet}
	 * @param date     {@link LocalDateTime}
	 */
	public Booking(Pet pet, Vet vet, Procedure procedure, LocalDateTime date) {
		this.pet = pet;
		this.vet = vet;
		this.procedure = procedure;
		this.date = date;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public Vet getVet() {
		return vet;
	}

	public String getId() {
	}

	public Pet getPet() {
		return pet;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setVet(Vet vet) {
		this.vet = vet;
	};

	public void setId(String id) {
		this.id = id;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	public void setProcedure(Procedure procedure) {
	    this.procedure = procedure;
	}
	
	public Procedure getProcedure() {
	    return procedure;
	}

}