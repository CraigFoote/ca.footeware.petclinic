/**
 * 
 */
package ca.footeware.petclinic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.footeware.petclinic.exceptions.LostPersonException;
import ca.footeware.petclinic.models.Doctor;
import ca.footeware.petclinic.models.Person;
import ca.footeware.petclinic.services.DoctorService;

/**
 * @author Footeware.ca
 *
 */
@Controller
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping("/add")
	public String getAddDoctorPage(Model model) {
		return "addDoctor";
	}

	@PostMapping("/add")
	public String createDoctor(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("phone") String phone, Model model)
			throws LostPersonException {
		Doctor doctor = new Doctor(firstName, lastName, email, phone);
		Doctor savedDoctor = doctorService.saveDoctor(doctor);
		if (savedDoctor == null) {
			throw new LostPersonException("Saved doctor not found.");
		}
		model.addAttribute("doctors", doctorService.getDoctors());
		return "doctors";
	}

	@GetMapping("{id}")
	String getDoctor(@PathVariable String id, Model model) {
		Person doctor = doctorService.getById(id);
		model.addAttribute("doctor", doctor);
		return "doctor";
	}

	@GetMapping
	String getDoctors(Model model) {
		List<Doctor> doctors = doctorService.getDoctors();
		doctors.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		model.addAttribute("doctors", doctors);
		return "doctors";
	}

	@GetMapping("/{id}/edit")
	String editDoctor(@PathVariable String id, Model model) {
		Person doctor = doctorService.getById(id);
		model.addAttribute("doctor", doctor);
		return "editDoctor";
	}

	@PostMapping("/edit")
	String updateDoctor(@RequestParam("id") String id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam(name = "petIds", required = false) List<String> petIds,
			Model model) {
		Doctor doctor = doctorService.getById(id);
		doctor.setFirstName(firstName);
		doctor.setLastName(lastName);
		doctor.setEmail(email);
		doctor.setPhone(phone);
		doctorService.saveDoctor(doctor);
		return getDoctors(model);
	}

}
