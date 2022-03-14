package fi.ohjelmistoprojekti1.TicketGuru.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/*
import javax.persistence.OneToMany;
*/

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketid;          // id
	private Boolean valid;          // Is ticket still valid. E.g not used
    private double ticketprice;     // gets value from tickettype OR user can input manually

    @ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;  // 
    
    @JsonBackReference
	@ManyToOne
    @JoinColumn(name = "saleid")
    private Sale sale;  //

    @ManyToOne
    @JoinColumn(name = "tickettypeid")
    private Tickettype tickettype;
    

    //CONSTRUCTORS

    
    public Ticket(Boolean valid, double ticketprice, Event event, Sale sale, Tickettype tickettype) {
        this.valid = valid;
        this.ticketprice = ticketprice;
        this.event = event;
        this.sale = sale;
        this.tickettype = tickettype;
    }

    public Ticket(Boolean valid, Event event, Sale sale, Tickettype tickettype) {
        this.valid = valid;
        this.event = event;
        this.sale = sale;
        this.tickettype = tickettype;
    }
    public Ticket(Boolean valid, Event event, Tickettype ticketType) {
        this.valid = valid;
        this.event = event;
        this.tickettype = ticketType;
    }
    public Ticket(Boolean valid, Event event, Sale sale) {
        this.valid = valid;
        this.event = event;
        this.sale = sale;
    }
    public Ticket(Boolean valid, Event event) {
        this.valid = valid;
        this.event = event;
    }

    public Ticket(Boolean valid) {
        this.valid = valid;
    }

    public Ticket() {
        super();
    }

     //GETTERS AND SETTERS

    public Long getTicketid() {
        return ticketid;
    }
    public void setTicketid(Long ticketid) {
        this.ticketid = ticketid;
    }
    public Boolean getValid() {
        return valid;
    }
    public void setValid(Boolean valid) {
        this.valid = valid;
    }
    public double getTicketprice() {
        return ticketprice;
    }
    public void setTicketprice(double ticketprice) {
        this.ticketprice = ticketprice;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public Sale getSale() {
        return sale;
    }
    public void setSale(Sale sale) {
        this.sale = sale;
    }
    public Tickettype getTickettype() {
        return tickettype;
    }
    public void setTickettype(Tickettype ticketType) {
        this.tickettype = ticketType;
    }

    @Override
    public String toString() {
        return "Ticket [event=" + event + ", sale=" + sale + ", ticketType=" + tickettype + ", ticketid=" + ticketid
                + ", ticketprice=" + ticketprice + ", valid=" + valid + "]";
    }

}
