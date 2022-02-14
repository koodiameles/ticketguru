package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventid;           // id
    @NotBlank(message = "Event must have a name/description")
	private String name;            // Name of the event
    private String location;      	// Location of the event. E.g. "Finlandia-Talo"
    //private String description;   // Description for the event // HUOM! Rautalankamallissa kuvaus=nimi // Toistaiseksi pois käytöstä
    private String city;            // Name of the city where the event will take place. E.g. "Helsinki"
    private int ticketcount;        // Number of tickets (max)
    private Date date;      		// Which day will the event take place. E.g "13.3.2003"
    private String starttime;       // Which hour will the event take place. E.g "18.30"
    private int duration;           // Estimated duration of the event in minutes. E.g "75" (1h15min)

    @ManyToOne
    @JoinColumn(name = "ticketid")
    private Ticket ticket;  //

	@ManyToOne
    @JoinColumn(name = "tickettypeid")
    private Tickettype tickettype;  // 


    //CONSTRUCTORS
    public Event(@NotBlank(message = "Event must have a name/description") String name, String location, String city,
            int ticketcount, Date date, String starttime, int duration) {
        this.name = name;
        this.location = location;
        this.city = city;
        this.ticketcount = ticketcount;
        this.date = date;
        this.starttime = starttime;
        this.duration = duration;
    }

    public Event(@NotBlank(message = "Event must have a name/description") String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Event [city=" + city + ", date=" + date + ", duration=" + duration + ", eventid=" + eventid
                + ", location=" + location + ", name=" + name + ", starttime=" + starttime + ", ticketcount="
                + ticketcount + "]";
    }
    
}
