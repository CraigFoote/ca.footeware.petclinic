/**
 *
 */
package ca.footeware.petclinic.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Footeware.ca
 */
@Entity
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int weight;
	private Gender gender;
	private int speciesId;
	private int ownerId;

	public Pet() {
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 * @param weight
	 * @param gender
	 * @param speciesId
	 * @param ownerId
	 */
	public Pet(String name, int weight, Gender gender, int speciesId, int ownerId) {
		this.name = name;
		this.weight = weight;
		this.gender = gender;
		this.speciesId = speciesId;
		this.ownerId = ownerId;
	}

	public Gender getGender() {
		return gender;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public int getSpeciesId() {
		return speciesId;
	}

	public int getWeight() {
		return weight;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
