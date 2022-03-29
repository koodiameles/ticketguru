package fi.ohjelmistoprojekti1.TicketGuru.web;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import org.springframework.web.server.ResponseStatusException;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Sale;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TicketRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;
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
	@GetMapping("/events/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public Optional<Event> findEvent(@PathVariable("id") Long eventid) {
		
		Optional<Event> event = eventrepository.findById(eventid);
		if (!event.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event id " + eventid + " not found");
		} // if event id doesn't exist => error
		
		return event;
	}

	// Get ALL events
	@GetMapping("/events")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Event>> getAllEvents() {
		List<Event>list =(List<Event>)eventrepository.findAll(); 
		return new ResponseEntity<>(list, HttpStatus.OK); 
	}
	
	// Add (POST) a new event
	@PostMapping("/events")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> addEvent(@Valid @RequestBody Event event, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingresult.getFieldError().getDefaultMessage());
		}
		return new ResponseEntity<>(eventrepository.save(event), HttpStatus.CREATED);
	}

	// Update event or add (PUT) a new event if id doesn't exist
	@PutMapping("/events/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Event updateEvent(@Valid @RequestBody Event newEvent, @PathVariable("id") Long eventid) {
		return eventrepository.findById(eventid)
				.map(event -> {
					event.setDescription(newEvent.getDescription());
					event.setLocation(newEvent.getLocation());
					event.setCity(newEvent.getCity());
					event.setTicketcount(newEvent.getTicketcount());
					event.setDatetime(newEvent.getDatetime());
					event.setDuration(newEvent.getDuration());
					return eventrepository.save(event);
				})
				.orElseGet(() -> {
					//return eventrepository.save(newEvent);
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event id " + eventid + " not found");
				});
	}

	// Delete event by id
	@DeleteMapping("/events/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> deleteEvent(@PathVariable("id") Long eventid) {
		
		Optional<Event> event = eventrepository.findById(eventid);
		if (!event.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event id " + eventid + " not found");
		} // if event id doesn't exist => error
		
		eventrepository.deleteById(eventid);
		HashMap<String, String> message = new HashMap<String, String>();
		message.put("message", "Deleted event " + event.get().getDescription() + " with the id " + eventid);
		return ResponseEntity.ok(message);
	}

}
