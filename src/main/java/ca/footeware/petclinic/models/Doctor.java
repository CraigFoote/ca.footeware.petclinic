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
public class Doctor extends Person {

	List<String> procedureIds = new ArrayList<>();

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

	public void addProcedures(List<String> procedureIds) {
		this.procedureIds.addAll(procedureIds);
	}

	public void addProcedure(String procedureId) {
		this.procedureIds.add(procedureId);
	}

	public List<String> getProcedures() {
		return this.procedureIds;
	}

}
