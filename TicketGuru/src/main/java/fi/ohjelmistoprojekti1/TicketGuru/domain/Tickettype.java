package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;

import javax.persistence.CascadeType;

//import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;



@Entity
public class Tickettype {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long tickettypeid; 
@NotBlank(message = "Tickettype must have a name/description")
private String name;	//Ex. student, adult, child, pension
private double price;	//Price of the tickettype

@ManyToOne
@JoinColumn(name = "eventid")
private Event event;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "tickettype")
private List<Ticket> tickets;

public Tickettype () {
	super(); 
}

public Tickettype(String name) {
	this.name = name; 
}


public Tickettype(String name, double price, Event event) {
	super(); 
	this.name = name;
	this.price = price;
	this.event = event; 
}

public Tickettype(String name, double price) {
	super(); 
	this.name = name;
	this.price = price;
}

public Tickettype(Long tickettypeid, String name, double price) {
	super(); 
	this.tickettypeid = tickettypeid; 
	this.name = name;
	this.price = price;
}

public Long getTickettypeid() {
	return tickettypeid;
}

public void setTickettypeid(Long tickettypeid) {
	this.tickettypeid = tickettypeid;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public Event getEvent() {
	return event;
}

public void setEvent(Event event) {
	this.event = event;
}

@Override
public String toString() {
	return "Tickettype [tickettypeid=" + tickettypeid + ", tickettype=" + name + ", price=" + price + "]";
} 


}
