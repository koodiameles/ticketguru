package fi.ohjelmistoprojekti1.TicketGuru.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.ohjelmistoprojekti1.TicketGuru.domain.EmployeeRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Role;
import fi.ohjelmistoprojekti1.TicketGuru.domain.RoleRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Sale;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Employee;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository emprepo;

	@Autowired
	RoleRepository rolerepo;

	// GET All employees
	@GetMapping("/employees")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = (List<Employee>) emprepo.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// Add (POST) new employee
	@PostMapping("/employees")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingresult.getFieldError().getDefaultMessage());
		}

		Optional<Role> roleResult = rolerepo.findById(employeeDTO.getRoleid()); // role in question
		if (!roleResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Role id " + employeeDTO.getRoleid() + " not valid");
		} // if the roleid doesn't exist => error

		Role role = roleResult.get(); // get the role in question

		try {
			for (Employee find : emprepo.findAll()) { // Check here, if the username is already in use
				if (find.getUsername().equals(employeeDTO.getUsername())) {
					throw new Exception();
				}
			}

			Employee create = new Employee(employeeDTO.getFirstname(),
					employeeDTO.getLastname(),
					employeeDTO.getUsername(),
					employeeDTO.getPassword(),
					role);
			if (employeeDTO.getFirstname() == null) {
				Map<String, String> response = new HashMap<>();
				response.put("message", "Firstname is missing");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			} else if (employeeDTO.getLastname() == null) {
				Map<String, String> response = new HashMap<>();
				response.put("message", "Lastname is missing");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			} else if (employeeDTO.getUsername() == null) {
				Map<String, String> response = new HashMap<>();
				response.put("message", "Username is missing");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			} else if (employeeDTO.getPassword() == null) {
				Map<String, String> response = new HashMap<>();
				response.put("message", "Password is missing");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			} else {
				emprepo.save(create);
				return new ResponseEntity<>(create, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			Map<String, String> response = new HashMap<>();
			response.put("message", "This username already exists");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	// Delete employee, if there are no sales
	@DeleteMapping("/employees/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") long employeeid) {

		Optional<Employee> employee = emprepo.findById(employeeid);
		Map<String, String> response = new HashMap<String, String>();

		if (employee.isPresent()) { // if employeeid is ok, we have to check if employee has made sales
			List<Sale> sales = employee.get().getSales();
			if (sales.size() > 0) {
				response.put("message", "Employee has sales and can not be deleted.");
				return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
			} else { // no sales, employee can be deleted
				emprepo.deleteById(employeeid);
				response.put("message", "Deleted employee " + employee.get().getFirstname() + " " + employee.get().getLastname()
						+ " with the id " + employeeid);
				return ResponseEntity.ok(response);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee id " + employeeid + " not found");
		}
	}

	// Update (PUT) employee information
	@PutMapping("/employees/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Employee updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable("id") long employeeid) {
		return emprepo.findById(employeeid)
				.map(employee -> {
					employee.setFirstname(employeeDTO.getFirstname());
					employee.setLastname(employeeDTO.getLastname());
					employee.setHashpassword(employeeDTO.getPassword());
					employee.setUsername(employeeDTO.getUsername());

					if (employeeDTO.getFirstname() == null) {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Firstname is missing");
					} else if (employeeDTO.getLastname() == null) {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lastname is missing");
					} else if (employeeDTO.getPassword() == null) {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is missing");
					} else if (employeeDTO.getUsername() == null) {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is missing");
					} else {
						return emprepo.save(employee);
					}
				})
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee id " + employeeid + " not found");
				});
	}
}
