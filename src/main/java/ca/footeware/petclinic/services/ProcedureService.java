/**
 *
 */
package ca.footeware.petclinic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.footeware.petclinic.models.Procedure;
import ca.footeware.petclinic.repositories.IProcedureRepository;

/**
 * @author Footeware.ca
 *
 */
@Service
public class ProcedureService {

	@Autowired
	private IProcedureRepository repository;

	public void delete(int id) {
		repository.deleteById(id);
	}

	public Procedure get(int id) {
		return repository.findById(id).get();
	}

	public Iterable<Procedure> getAll() {
		return repository.findAll();
	}

	public Procedure save(Procedure procedure) {
		return repository.save(procedure);
	}

}
