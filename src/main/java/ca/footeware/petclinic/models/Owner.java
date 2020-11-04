/**
 * 
 */
package ca.footeware.petclinic.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Footeware.ca
 *
 */
public class Owner extends Person {

	List<String> petIds = new ArrayList<>();

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

	public void addPets(List<String> petIds) {
		this.petIds.addAll(petIds);
	}

	public void setPets(List<String> petIds) {
		if (petIds == null) {
			this.petIds = new ArrayList<>();
		} else {
			this.petIds = petIds;
		}
	}

	public List<String> getPets() {
		return petIds;
	}

}
