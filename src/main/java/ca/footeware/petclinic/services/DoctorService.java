/**
 * 
 */
package ca.footeware.petclinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.footeware.petclinic.models.Doctor;
import ca.footeware.petclinic.repositories.IDoctorRepository;

/**
 * @author Footeware.ca
 *
 */
@Service
public class DoctorService {

	@Autowired
	private IDoctorRepository repository;

	public Doctor getById(String id) {
		return repository.findById(id).get();
	}

}
