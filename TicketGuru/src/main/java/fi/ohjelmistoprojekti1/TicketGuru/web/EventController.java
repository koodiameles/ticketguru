package fi.ohjelmistoprojekti1.TicketGuru.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TicketRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository eventrepository; 
	
	@Autowired
	private TicketRepository ticketrepository; 
	
	@Autowired
	private TickettypeRepository tickettyperepository; 


	// Get event by id
    @RequestMapping(value="/event/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Event> findEventRest(@PathVariable("id") Long eventid) {	
    	return eventrepository.findById(eventid);
    } 

	// Get ALL events
    @RequestMapping(value="/events", method = RequestMethod.GET)
    public @ResponseBody List<Event> eventListRest() {	
        return (List<Event>) eventrepository.findAll();
    }    
    
	// Add (POST) a new event
    
	// Delete event by id
    @DeleteMapping(value="/event/{id}")
    public @ResponseBody List<Event> deleteEventRest(@PathVariable("id") Long eventid) {
    	eventrepository.deleteById(eventid);
        return (List<Event>) eventrepository.findAll();

    }
    
	// Edit (PUT) event 
}
