package fi.ohjelmistoprojekti1.TicketGuru.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tickettype {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long tickettypeid; 
private String tickettype;
private double price;

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
