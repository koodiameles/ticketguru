package fi.ohjelmistoprojekti1.TicketGuru;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Employee;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EmployeeRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.RoleRepository;


@DataJpaTest
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository empRepo; 
	
	@Autowired
	private RoleRepository roleRepo; 
	
	@Test
	public void findByLastnameShouldReturnEmployee() {
		 List<Employee> employees = empRepo.findByLastname("Varpunen");
	        
	        assertThat(employees).hasSize(1);
	        assertThat(employees.get(0).getFirstname()).isEqualTo("cpt Jaakko");
	}
	
	@Test
    public void createNewEmployee() {
    	Employee employee = new Employee("Mickey", "Mouse", "mouse", "075a421a01fe4984b4ade4a89afec861f9a435f54b5bced6d0a0e5a8792e521c");
    	empRepo.save(employee);
    	assertThat(employee.getEmployeeid()).isNotNull();
    }    

}
