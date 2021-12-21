/**
 *
 */
package ca.footeware.petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@GetMapping("/add")
	public String getAddProcedurePage(Model model) {
		return "addProcedure";
	}
}
