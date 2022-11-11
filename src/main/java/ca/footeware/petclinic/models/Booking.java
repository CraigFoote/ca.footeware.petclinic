/**
 *
 */
package ca.footeware.petclinic.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Footeware.ca
 */
@Entity
@Table(name = "BOOKING")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int petId;
	private int vetId;
	private int procedureId;
	private LocalDateTime date;

	public Booking() {
	}

	/**
	 * Constructor.
	 *
	 * @param petId
	 * @param vetId
	 * @param procedureId
	 * @param date
	 */
	public Booking(int petId, int vetId, int procedureId, LocalDateTime date) {
		this.petId = petId;
		this.vetId = vetId;
		this.procedureId = procedureId;
		this.date = date;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public int getPetId() {
		return petId;
	}

	public int getProcedureId() {
		return procedureId;
	}

	public int getVetId() {
		return vetId;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public void setProcedureId(int procedureId) {
		this.procedureId = procedureId;
	}

	public void setVetId(int vetId) {
		this.vetId = vetId;
	}
}