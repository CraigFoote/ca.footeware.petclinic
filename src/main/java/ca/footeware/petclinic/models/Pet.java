/**
 * 
 */
package ca.footeware.petclinic.models;

import org.springframework.data.annotation.Id;

/**
 * @author Footeware.ca
 *
 */
public abstract class Pet {

	public enum Gender {
		MALE, FEMALE
	}

	@Id
	String id;
	String name;
	String species;
	String breed;
	int weight;
	Gender gender;
	boolean fixed;
	String ownerName;
	String ownerPhone;
	String notes;

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
	 * @param notes
	 */
	public Pet(String id, String name, String species, String breed, int weight, Gender gender, boolean fixed,
			String ownerName, String ownerPhone, String notes) {
		this.id = id;
		this.name = name;
		this.species = species;
		this.breed = breed;
		this.weight = weight;
		this.gender = gender;
		this.fixed = fixed;
		this.ownerName = ownerName;
		this.ownerPhone = ownerPhone;
		this.notes = notes;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the fixed
	 */
	public boolean isFixed() {
		return fixed;
	}

	/**
	 * @param fixed the fixed to set
	 */
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	/**
	 * @return the breed
	 */
	public String getSpecies() {
		return species;
	}

	/**
	 * @param breed the species to set
	 */
	public void setSpecies(String species) {
		this.species = species;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return the ownerPhone
	 */
	public String getOwnerPhone() {
		return ownerPhone;
	}

	/**
	 * @param ownerPhone the ownerPhone to set
	 */
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the breed
	 */
	public String getBreed() {
		return breed;
	}

	/**
	 * @param breed the breed to set
	 */
	public void setBreed(String breed) {
		this.breed = breed;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
