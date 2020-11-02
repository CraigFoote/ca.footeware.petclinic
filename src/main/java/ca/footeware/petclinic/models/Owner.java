/**
 * 
 */
package ca.footeware.petclinic.models;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Footeware.ca
 *
 */
public class Owner extends Person {

	Set<String> petIds = new HashSet<>();

	/**
	 * Constructor.
	 * 
	 * @param firstName {@link String}
	 * @param lastName  {@link String}
	 * @param email     {@link String}
	 * @param phone     {@link String}
	 */
	public Owner(String firstName, String lastName, String email, String phone) {
		super(firstName, lastName, email, phone);
	}

	public void addPet(String petId) {
		petIds.add(petId);
	}

	public void addPets(Set<String> petIds) {
		petIds.addAll(petIds);
	}

	public Set<String> getPets() {
		return petIds;
	}

}
