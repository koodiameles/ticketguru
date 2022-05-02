package fi.ohjelmistoprojekti1.TicketGuru.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Sale;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;
import fi.ohjelmistoprojekti1.TicketGuru.web.TickettypeDTO; 


@RestController
public class TickettypeController {
	
	@Autowired
	private EventRepository eventrepository;
	
	@Autowired
	private TickettypeRepository tickettyperepository;

	// Get one tickettype
	@GetMapping("/tickettypes/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public Optional<Tickettype> findTickettypeRest(@PathVariable("id") Long tickettypeid) {
	Optional <Tickettype> tickettypeResult = tickettyperepository.findById(tickettypeid); 
	if (!tickettypeResult.isPresent()) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tickettype id " + tickettypeid + " is not valid");
		}
	return tickettyperepository.findById(tickettypeid); 
	}
	
	// Get all tickettypes
	@GetMapping("/tickettypes")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Tickettype>> getAllTickettypes() {
		List<Tickettype>list=(List<Tickettype>) tickettyperepository.findAll(); 
		return new ResponseEntity<>(list, HttpStatus.OK); 
	}
	
	
	
	// Add (POST) a new tickettype
	@PostMapping("/tickettypes")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> addTickettype(@Valid @RequestBody TickettypeDTO tickettype, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingresult.getFieldError().getDefaultMessage()); 
		}

		Optional<Event> event = eventrepository.findById(tickettype.getEvent());

		if (! event.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not valid");
		}
		
		Tickettype newtype = new Tickettype(tickettype.getName(), tickettype.getPrice(), event.get());
		
			List <Tickettype> list = tickettyperepository.findByEvent(event.get());
			for(Tickettype type:list) {
				if(type.getName().equalsIgnoreCase(newtype.getName())) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tickettype already exists."); 
				}
			}
			
		return new ResponseEntity<>(tickettyperepository.save(newtype), HttpStatus.CREATED);
	}

}
