package fi.ohjelmistoprojekti1.TicketGuru.web;

import java.util.HashMap;
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
	@GetMapping("/events/{id}")
	public Optional<Event> findEventRest(@PathVariable("id") Long eventid) {
		return eventrepository.findById(eventid);
	}

	// Get ALL events
	@GetMapping("/events")
	public List<Event> eventListRest() {
		return eventrepository.findAll();
	}

	// Add (POST) a new event
	@PostMapping("/events")
	public Event addEvent(@RequestBody Event event) {
		eventrepository.save(event);
		return event;
	}

	// Update event or add (PUT) a new event if id doesn't exist
	@PutMapping("/events/{id}")
	public Event updateEvent(@RequestBody Event newEvent, @PathVariable("id") Long eventid) {
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
					return eventrepository.save(newEvent);
				});
	}

	// Delete event by id
	@DeleteMapping("/events/{id}")
	public ResponseEntity<?> deleteEventRest(@PathVariable("id") Long eventid) {
		Optional<Event> event = eventrepository.findById(eventid);
		eventrepository.deleteById(eventid);
		HashMap<String, String> message = new HashMap<String, String>();
		message.put("message", "Deleted event " + event.get().getDescription() + " with the id " + eventid);
		return ResponseEntity.ok(message);
	}

}
