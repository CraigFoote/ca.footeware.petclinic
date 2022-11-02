/**
 *
 */
package ca.footeware.petclinic.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;

/**
 * @author Footeware.ca
 */
public class Pet {

	public enum Gender {
		MALE, FEMALE
	}

	@Id
	private UUID id = UUID.randomUUID();
	private String name;
	private Species species;
	private int weight;
	private Gender gender;
	private Owner owner;

	/**
	 * Constructor.
	 *
	 * @param name    {@link String}
	 * @param species {@link Species}
	 * @param weight  int
	 * @param gender  {@link Gender}
	 * @param owner   {@link Owner}
	 */
	public Pet(String name, Species species, int weight, Gender gender, Owner owner) {
		this.name = name;
		this.species = species;
		this.weight = weight;
		this.gender = gender;
		this.owner = owner;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return the owner
	 */
	public Owner getOwner() {
		return owner;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the species
	 */
	public Species getSpecies() {
		return species;
	}

	/**
	 * @param species the species to set
	 */
	public void setSpecies(Species species) {
		this.species = species;
	}

}
