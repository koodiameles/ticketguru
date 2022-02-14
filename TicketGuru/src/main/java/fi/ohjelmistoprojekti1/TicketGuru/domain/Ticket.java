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


@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketid;          // id
	private Boolean valid;          // Is ticket still valid. E.g not used

    @ManyToOne
    @JoinColumn(name = "eventid")
    private Event event;  // 

    /*
	@ManyToOne
    @JoinColumn(name = "saleventid")
    private SaleEvent salevent;  //
    */


    //CONSTRUCTORS
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Ticket [event=" + event + ", ticketid=" + ticketid + ", valid=" + valid + "]";
    }

}
