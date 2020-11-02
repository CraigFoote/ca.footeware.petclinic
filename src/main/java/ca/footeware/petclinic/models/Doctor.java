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
public class Doctor extends Person {

	Set<String> procedureIds = new HashSet<>();

	/**
	 * Constructor.
	 * 
	 * @param firstName {@link String}
	 * @param lastName  {@link String}
	 * @param email     {@link String}
	 * @param phone     {@link String}
	 */
	Doctor(String firstName, String lastName, String email, String phone) {
		super(firstName, lastName, email, phone);
	}

	public void addProcedures(Set<String> procedureIds) {
		this.procedureIds.addAll(procedureIds);
	}

	public void addProcedure(String procedureId) {
		this.procedureIds.add(procedureId);
	}

	public Set<String> getProcedures() {
		return this.procedureIds;
	}

}
