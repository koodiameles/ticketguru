package fi.ohjelmistoprojekti1.TicketGuru.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


@RestController
public class TickettypeController {
	
	@Autowired
	private EventRepository eventrepository;
	
	@Autowired
	private TickettypeRepository tickettyperepository;

	// Get one tickettype
	@GetMapping("/tickettypes/{id}")
	public Optional<Tickettype> findTickettypeRest(@PathVariable("id") Long tickettypeid) {
	Optional <Tickettype> tickettypeResult = tickettyperepository.findById(tickettypeid); 
	if (!tickettypeResult.isPresent()) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tickettype id " + tickettypeid + " is not valid");
		}
	return tickettyperepository.findById(tickettypeid); 
	}
	
	// Get all tickettypes
	@GetMapping("/tickettypes")
	public ResponseEntity<List<Tickettype>> getAllTickettypes() {
		List<Tickettype>list=(List<Tickettype>) tickettyperepository.findAll(); 
		if(list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no tickettypes"); 
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK); 
		}
		
	}
	
	// Add (POST) a new event
		@PostMapping("/tickettypes")
		@ResponseStatus(HttpStatus.CREATED)
		public Tickettype addTickettype(@Valid @RequestBody Tickettype tickettype, BindingResult bindingresult) {
			if (bindingresult.hasErrors()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingresult.getFieldError().getDefaultMessage()); 
			}
			tickettyperepository.save(tickettype);
			return tickettype;
		}
		

}
