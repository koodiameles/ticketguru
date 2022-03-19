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

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class Employee {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	
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
	private String hashpassword;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Sale> sales;
	
	@ManyToOne
    @JoinColumn(name = "roleid")
	@JsonIgnore
    private Role role; 

	public Employee() {
	}
	
	public Employee(String firstname, String lastname, String username, String hashpassword, Role role) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.hashpassword = hashpassword;
		this.role = role;
	}

	public Employee(String firstname, String lastname, String username, String hashpassword) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.hashpassword = hashpassword;
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

	public String getHashpassword() {
		return hashpassword;
	}

	public void setHashpassword(String hashpassword) {
		this.hashpassword = PASSWORD_ENCODER.encode(hashpassword);
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", firstname=" + firstname + ", hashpassword=" + hashpassword
				+ ", lastname=" + lastname + ", role=" + role + ", username=" + username + "]";
	}
	
}
