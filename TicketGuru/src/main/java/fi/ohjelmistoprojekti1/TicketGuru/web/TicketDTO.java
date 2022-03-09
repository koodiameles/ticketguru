package fi.ohjelmistoprojekti1.TicketGuru.web;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;

public class TicketDTO {
	private Long eventid;
	private Boolean valid;
	private String tickettype;
	private String description;    // Event desc
	private double price;			// Tickettype price
 
	

	public TicketDTO(Tickettype entity_tickettype, Event entity_event) {
        this.eventid = entity_event.getEventid();
        this.tickettype = entity_tickettype.getName();
        this.price = entity_tickettype.getPrice();
        this.description = entity_event.getDescription();
    }
	public TicketDTO() {
		super();
	}


	public String getTickettype() {
		return tickettype;
	}


	public void setTickettype(String tickettype) {
		this.tickettype = tickettype;
	}

	public Long getEventid() {
		return eventid;
	}
	public void setEventid(Long eventid) {
		this.eventid = eventid;
	}
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "TicketDTO [description=" + description + ", eventid=" + eventid + ", price=" + price + ", tickettype="
				+ tickettype + ", valid=" + valid + "]";
	}

	
}