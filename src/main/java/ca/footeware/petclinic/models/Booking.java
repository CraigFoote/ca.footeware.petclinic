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
	private String petId;
	private String doctorId;
	private String procedureId;
	private LocalDateTime date;

	/**
	 * Constructor.
	 * 
	 * @param petId    {@link String}
	 * @param doctorId {@link String}
	 * @param date     {@link LocalDateTime}
	 */
	public Booking(String petId, String doctorId, String procedureId, LocalDateTime date) {
		this.petId = petId;
		this.doctorId = doctorId;
		this.procedureId 
		this.date = date;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public String getId() {
	}

	public String getPetId() {
		return petId;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	};

	public void setId(String id) {
		this.id = id;
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}
	
	public void setProcedureId(String procedureId) {
	    this.procedureId = procedureId;
	}
	
	public String getProcedureId() {
	    return procedureId;

}