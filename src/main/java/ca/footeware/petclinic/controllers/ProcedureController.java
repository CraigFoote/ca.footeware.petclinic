/**
 *
 */
package ca.footeware.petclinic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.exceptions.ProcedureException;
import ca.footeware.petclinic.models.Procedure;
import ca.footeware.petclinic.models.Species;
import ca.footeware.petclinic.services.ProcedureService;

/**
 * @author Footeware.ca
 *
 */
@Controller
@RequestMapping("/procedures")
public class ProcedureController {

	@Autowired
	private ProcedureService procedureService;

	@GetMapping
	public String getProcedures(Model model) {
		List<Procedure> procedures = procedureService.getAll();
		model.addAttribute("procedures", procedures);
		return "procedures";
	}

	@GetMapping("/add")
	public String getAddProcedurePage(Model model) {
		return "addProcedure";
	}

	@DeleteMapping("/{id}")
	public String deleteSpecies(@PathVariable("id") String id, Model model) {
		procedureService.delete(id);
		return getProcedures(model);
	}

	@PostMapping
	String createProcedure(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "cost", required = true) double cost, Model model) throws ProcedureException {
		Procedure savedProcedure = procedureService.save(new Procedure(name, cost));
		if (savedProcedure == null) {
			throw new ProcedureException("Saved procedure not found.");
		}
		return getProcedures(model);
	}
}
