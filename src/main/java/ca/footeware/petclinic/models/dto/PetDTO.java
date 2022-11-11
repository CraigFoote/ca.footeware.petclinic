/**
 *
 */
package ca.footeware.petclinic.models.dto;

import ca.footeware.petclinic.models.Owner;
import ca.footeware.petclinic.models.Pet;
import ca.footeware.petclinic.models.Species;

/**
 * @author craig
 *
 */
public class PetDTO {

	private Pet pet;
	private Species species;
	private Owner owner;

	/**
	 * Constructor.
	 *
	 * @param pet
	 * @param species
	 * @param owner
	 */
	public PetDTO(Pet pet, Species species, Owner owner) {
		this.pet = pet;
		this.species = species;
		this.owner = owner;
	}

	public Owner getOwner() {
		return owner;
	}

	public Pet getPet() {
		return pet;
	}

	public Species getSpecies() {
		return species;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

}
