/**
 * 
 */
package ca.footeware.petclinic.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import ca.footeware.petclinic.models.Doctor;

/**
 * @author Footeware.ca
 *
 */
public interface IDoctorRepository extends MongoRepository<Doctor, String> {

}
