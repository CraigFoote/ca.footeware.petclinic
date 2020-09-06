/**
 * 
 */
package ca.footeware.petclinic.models;

/**
 * @author Footeware.ca
 *
 */
public class Dog extends Pet {

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param name
	 * @param species
	 * @param breed
	 * @param weight
	 * @param gender
	 * @param fixed
	 * @param ownerName
	 * @param ownerPhone
	 */
	public Dog(String id, String name, String species, String breed, int weight, Gender gender, boolean fixed, String ownerName,
			String ownerPhone) {
		super(id, name, species, breed, weight, gender, fixed, ownerName, ownerPhone);
	}

}
