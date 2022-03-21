package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "employeeid", nullable = false, updatable = false)
	private Long employeeid;
	
	@NotBlank(message = "Employee must have a firstname")
	private String firstname;
	
	@NotBlank(message = "Employee must have a lastname")
	private String lastname;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	@JsonIgnore
	private String hashPassword;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Sale> sales;
	

	@JsonIgnore
    private String role; 

	public Employee() {
	}
	
	public Employee(String firstname, String lastname, String username, String hashPassword) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.hashPassword = hashPassword;
	}
	
	

	public Employee(@NotBlank(message = "Employee must have a firstname") String firstname,
			@NotBlank(message = "Employee must have a lastname") String lastname, String username, String hashPassword, String role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.hashPassword = hashPassword;
		this.role = role;
	}

	public Long getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Long employeeid) {
		this.employeeid = employeeid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", firstname=" + firstname + ", lastname=" + lastname + ", sales="
				+ sales + ", role=" + role + "]";
	}
	
}
