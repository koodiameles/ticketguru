package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleid;
	@NotBlank(message = "Role must have a description")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	private List<Employee> employees;

	public Role(String description) {
		super();
		this.description = description;
	}

	public Role() {
		super();
	}

	public Long getroleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Role [description=" + description + ", roleid=" + roleid + "]";
	}

}
