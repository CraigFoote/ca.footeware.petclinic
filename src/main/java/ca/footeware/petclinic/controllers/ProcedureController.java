/**
 *
 */
package ca.footeware.petclinic.controllers;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

	@PostMapping
	public String createProcedure(@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "cost", required = true) double cost, Model model) throws ProcedureException {
		Procedure savedProcedure = procedureService.save(new Procedure(name, cost));
		if (savedProcedure == null) {
			throw new ProcedureException("'" + name + "' did not save");
		}
		return getProcedures(model);
	}

	@DeleteMapping("/{id}")
	public String deleteSpecies(@PathVariable("id") UUID id, Model model) {
		procedureService.delete(id);
		return getProcedures(model);
	}

	@GetMapping("/{id}")
	public String editProcedure(@PathVariable UUID id, Model model) {
		Procedure procedure = procedureService.get(id);
		model.addAttribute("procedure", procedure);
		return "editProcedure";
	}

	@GetMapping("/add")
	public String getAddProcedurePage(Model model) {
		return "addProcedure";
	}

	@GetMapping
	public String getProcedures(Model model) {
		// FIXME, necessary to get accurate inventory
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Procedure> procedures = procedureService.getAll();
		Collections.sort(procedures, (o1, o2) -> o1.getName().compareTo(o2.getName()));
		model.addAttribute("procedures", procedures);
		return "procedures";
	}

	@PostMapping("/update")
	public String updateProcedure(@RequestParam(name = "id", required = true) UUID id,
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "cost", required = true) double cost, Model model) throws ProcedureException {
		Procedure procedure = procedureService.get(id);
		procedure.setName(name);
		procedure.setCost(cost);
		Procedure savedProcedure = procedureService.save(procedure);
		if (savedProcedure == null) {
			throw new ProcedureException("Saved procedure not found.");
		}
		return getProcedures(model);
	}
}
