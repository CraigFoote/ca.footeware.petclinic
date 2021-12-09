/**
 * 
 */
package ca.footeware.petclinic.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;

/**
 * @author Footeware.ca
 *
 */
public class Person {

	@Id
	String id = UUID.randomUUID().toString();
	String firstName;
	String lastName;
	String email;
	String phone;

	/**
	 * Constructor.
	 * 
	 * @param firstName {@link String}
	 * @param lastName  {@link String}
	 * @param email     {@link String}
	 * @param phone     {@link String}
	 */
	public Person(String firstName, String lastName, String email, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
