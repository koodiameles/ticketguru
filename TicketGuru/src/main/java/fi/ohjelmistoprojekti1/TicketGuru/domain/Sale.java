package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long saleid;
	@NotNull(message = "Sale must have a datetime")
	private Date datetime;
	
	@ManyToOne
	@JoinColumn(name = "employeeid")
	private Employee employee;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sale")
	private List<Ticket> tickets;   // List of tickets for this sale

	public Sale(Date datetime, Employee employee) {
		super();
		this.datetime = datetime;
		this.employee = employee;
	}

	public Sale(Date datetime) {
		super();
		this.datetime = datetime;
	}

	public Sale() {
		super();
	}

	public Long getSaleid() {
		return saleid;
	}

	public void setSaleid(Long saleid) {
		this.saleid = saleid;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Sale [saleid=" + saleid + ", datetime=" + datetime + ", employee=" + employee + "]";
	}
	
}
