package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	List<Employee> findByLastname(String lastname);

	Employee findByUsername(String username);

	List<Employee>findAll(); 
	
}
