package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;



@Entity
public class Tickettype {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long tickettypeid; 
@NotBlank(message = "Tickettype must have a name/description")
private String tickettype;	//Ex. student, adult, child, pension
private double price;	//Price of the tickettype

@ManyToOne
@JoinColumn(name = "eventid")
private Event event;


public Tickettype(String tickettype, double price) {
	super();
	this.tickettype = tickettype;
	this.price = price;
}

public Long getTickettypeid() {
	return tickettypeid;
}

public void setTickettypeid(Long tickettypeid) {
	this.tickettypeid = tickettypeid;
}

public String getTickettype() {
	return tickettype;
}

public void setTickettype(String tickettype) {
	this.tickettype = tickettype;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

@Override
public String toString() {
	return "Tickettype [tickettypeid=" + tickettypeid + ", tickettype=" + tickettype + ", price=" + price + "]";
} 


}
