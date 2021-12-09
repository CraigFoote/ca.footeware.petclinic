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
public class Pet {

	public enum Gender {
		MALE, FEMALE
	}

	@Id
	String id = UUID.randomUUID().toString();
	String name;
	String speciesId;
	int weight;
	Gender gender;
	String ownerId;

	/**
	 * Constructor.
	 * 
	 * @param name      {@link String}
	 * @param speciesId {@link String}
	 */
	public Pet(String name, String speciesId) {
		this.name = name;
		this.speciesId = speciesId;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return the ownerId
	 */
	public String getOwnerId() {
		return ownerId;
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
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the speciesId
	 */
	public String getSpeciesId() {
		return speciesId;
	}

	/**
	 * @param speciesId the speciesId to set
	 */
	public void setSpeciesId(String speciesId) {
		this.speciesId = speciesId;
	}

}
