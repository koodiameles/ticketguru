package fi.ohjelmistoprojekti1.TicketGuru.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Role;

public class EmployeeDTO {
	
	/*public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();*/
	
	
	private String firstname; 
	
	private String lastname;
	
	private String username;
	private long roleid; 
	
	private String password;
	
	
	
	
	public EmployeeDTO(String firstname,String lastname, String username,
			String password, long roleid) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.roleid = roleid; 
	}


	public EmployeeDTO() {
		super();
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public long getRoleid() {
		return roleid;
	}


	public void setRole(long roleid) {
		this.roleid = roleid;
	}


	@Override
	public String toString() {
		return "EmployeeDTO [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", role=" + roleid + "]";
	}


	

}
