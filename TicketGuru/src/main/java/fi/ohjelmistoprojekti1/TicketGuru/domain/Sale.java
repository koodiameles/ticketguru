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

@Entity
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long saleid;
	private Date datetime;
	private int amount;    // Sum of the tickets included in the sale
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sale")
	private List<Ticket> tickets;
	
	@ManyToOne
	@JoinColumn(name = "employeeid")
	private Employee employee;

	public Sale(Long saleid, Date datetime, int amount, Employee employee) {
		super();
		this.saleid = saleid;
		this.datetime = datetime;
		this.amount = amount;
		this.employee = employee;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
		return "Sale [saleid=" + saleid + ", datetime=" + datetime + ", amount=" + amount + ", tickets=" + tickets
				+ ", employee=" + employee + "]";
	}
	
}
