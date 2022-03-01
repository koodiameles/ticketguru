package fi.ohjelmistoprojekti1.TicketGuru.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TicketRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

@RestController
public class EventController {

	@Autowired
	private EventRepository eventrepository;

	@Autowired
	private TicketRepository ticketrepository;

	@Autowired
	private TickettypeRepository tickettyperepository;

	// Get event by id
	@GetMapping("/event/{id}")
	public Optional<Event> findEventRest(@PathVariable("id") Long eventid) {
		return eventrepository.findById(eventid);
	}

	// Get ALL events
	@GetMapping("/events")
	public List<Event> eventListRest() {
		return eventrepository.findAll();
	}

	// Add (POST) a new event
	@PostMapping("/event")
	public Event addEvent(@RequestBody Event event) {
		eventrepository.save(event);
		return event;
	}

	// Update event or add (PUT) a new event
	@PutMapping("/event")
	public Event saveOrUpdateEvent(@RequestBody Event event) {
		eventrepository.save(event);
		return event;
	}

	// Delete event by id
	@DeleteMapping("/event/{id}")
	public List<Event> deleteEventRest(@PathVariable("id") Long eventid) {
		eventrepository.deleteById(eventid);
		return eventrepository.findAll();
	}
	
}
