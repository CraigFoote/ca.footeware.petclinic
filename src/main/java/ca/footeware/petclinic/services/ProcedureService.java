/**
 *
 */
package ca.footeware.petclinic.services;

import java.util.List;

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

	public Procedure get(String id) {
		return repository.findById(id).get();
	}

	public List<Procedure> getAll() {
		return repository.findAll();
	}

}
