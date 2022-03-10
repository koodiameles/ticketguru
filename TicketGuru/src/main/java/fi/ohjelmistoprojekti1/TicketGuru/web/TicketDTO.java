package fi.ohjelmistoprojekti1.TicketGuru.web;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;

public class TicketDTO {
	private Long ticketid;
	private Long eventid;
	private Long tickettypeid;
	private String tickettype;	// Tickettype name E.g "child"
	private Boolean valid;
	private String description;    // Event desc
	private double price;			// Tickettype price
 

	//Not in use. Constructor using entities.
	public TicketDTO(Tickettype entity_tickettype, Event entity_event) {
        this.eventid = entity_event.getEventid();
        this.tickettypeid = entity_tickettype.getTickettypeid();
        this.price = entity_tickettype.getPrice();
        this.description = entity_event.getDescription();
    }

	// CONSTRUCTORS
	public TicketDTO() {
		super();
	}

	// GETTERS AND SETTERS
	public String getTickettype() {
		return tickettype;
	}
	public Long getTicketid() {
		return ticketid;
	}
	public void setTicketid(Long ticketid) {
		this.ticketid = ticketid;
	}

	public void setTickettype(String tickettype) {
		this.tickettype = tickettype;
	}
	public Long getTickettypeid() {
		return tickettypeid;
	}
	public void setTickettypeid(Long tickettypeid) {
		this.tickettypeid = tickettypeid;
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
		return "TicketDTO [description=" + description + ", eventid=" + eventid + ", price=" + price + ", tickettypeid="
				+ tickettypeid + ", valid=" + valid + "]";
	}

	
}