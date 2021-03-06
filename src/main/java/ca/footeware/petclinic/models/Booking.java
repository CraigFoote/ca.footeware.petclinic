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
	String id = UUID.randomUUID().toString();
	String name;
	String petId;
	String doctorId;
	LocalDateTime date;

	/**
	 * Constructor.
	 * 
	 * @param name     {@link String}
	 * @param petId    {@link String}
	 * @param doctorId {@link String}
	 * @param date     {@link LocalDateTime}
	 */
	public Booking(String name, String petId, String doctorId, LocalDateTime date) {
		this.name = name;
		this.petId = petId;
		this.doctorId = doctorId;
		this.date = date;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPetId() {
		return petId;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}

}