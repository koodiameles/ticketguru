package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotBlank;
import javax.persistence.OneToMany;

/*
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
*/


@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventid;           // id
    @NotBlank(message = "Event must have a name/description")
    private String description;     // Description/name for the event
    private String location;      	// Location of the event. E.g. "Finlandia-Talo"
    private String city;            // Name of the city where the event will take place. E.g. "Helsinki"
    private int ticketcount;        // Number of tickets (max)
    private Date datetime;      	// Which day and time will the event take place. E.g "2022-05-22T18:00:00"
    private int duration;           // Estimated duration of the event in minutes. E.g "75" (1h15min)
    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Ticket> tickets;   // List of tickets for this event

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Tickettype> tickettypes;   //List of tickettypes for this event



    //CONSTRUCTORS
    public Event(@NotBlank(message = "Event must have a name/description") String description, String location, String city,
            int ticketcount, Date datetime, int duration) {
        this.description = description;
        this.location = location;
        this.city = city;
        this.ticketcount = ticketcount;
        this.datetime = datetime;
        this.duration = duration; 
    }
    
    public Event(@NotBlank(message = "Event must have a name/description") String description, String location, String city,
            int ticketcount, Date datetime, int duration, List<Tickettype> tickettype) {
        this.description = description;
        this.location = location;
        this.city = city;
        this.ticketcount = ticketcount;
        this.datetime = datetime;
        this.duration = duration;
        this.tickettypes = tickettypes; 
    }

    public Event(@NotBlank(message = "Event must have a name/description") String description) {
        this.description = description;
    }

    public Event() {
        super();
    }


    //GETTERS AND SETTERS
    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTicketcount() {
        return ticketcount;
    }

    public void setTicketcount(int ticketcount) {
        this.ticketcount = ticketcount;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public List<Tickettype> getTickettypes() {
		return tickettypes;
	}

	public void setTickettypes(List<Tickettype> tickettypes) {
		this.tickettypes = tickettypes;
	}

	//TOSTRING
    @Override
    public String toString() {
        return "Event [city=" + city + ", datetime=" + datetime + ", description=" + description + ", duration="
                + duration + ", eventid=" + eventid + ", location=" + location + ", ticketcount=" + ticketcount + "]";
    }
}