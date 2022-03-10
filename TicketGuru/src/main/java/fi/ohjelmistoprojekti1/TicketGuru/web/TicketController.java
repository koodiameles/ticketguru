package fi.ohjelmistoprojekti1.TicketGuru.web;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Sale;
import fi.ohjelmistoprojekti1.TicketGuru.domain.SaleRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Ticket;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TicketRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

@RestController
public class TicketController {

	@Autowired
	private TicketRepository ticketsRepo;

	@Autowired
	private SaleRepository salesRepo;

	@Autowired
	private TickettypeRepository tickettypesRepo;
	
	@Autowired
	private EventRepository eventsRepo;	
	

	// Add (POST) a new ticket to sale
	@PostMapping("/sales/{id}/tickets")
	public TicketDTO addTicketToSale(@PathVariable long id, @RequestBody TicketDTO ticketDTO) {
		
		Optional<Sale> saleResult = salesRepo.findById(id); // Sale for which the ticket will be added
		if (!saleResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sale id not valid" + id);
		} // if the saleid doesn't exist => error
		
		Optional <Event> eventResult = eventsRepo.findById(ticketDTO.getEventid()); //Event in question
		if (!eventResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event id not valid" + ticketDTO.getEventid());
		} // if the eventid doesn't exist => error

		Optional <Tickettype> tickettypeResult = tickettypesRepo.findById(ticketDTO.getTickettypeid()); //Tickettype in question
		if (!tickettypeResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tickettype id not valid" + ticketDTO.getTickettypeid());
		} // if the tickettypeid doesn't exist => error 
		
		Sale sale = saleResult.get(); // get the sale in question
		Event event = eventResult.get(); // get the event in question
		Tickettype tickettype = tickettypeResult.get(); // get the event in question

		// ADDING TICKET TO DATABASE
		Ticket ticket = new Ticket(true, event, sale, tickettype); // generate ticket (is valid?, which event?, which sale?, which tickettype?)
		// set ticketprice
		// ! API accepts manual price input (User can input manually). If not set, then use the default value from the tickettype
		if (ticketDTO.getPrice() != 0) {
			ticket.setTicketprice(ticketDTO.getPrice()); 
		} else {
			ticket.setTicketprice(ticket.getTickettype().getPrice()); 
		}
		ticketsRepo.save(ticket); // save the ticket to the ticketrepository

		// DATA WHICH WILL BE RETURNED
		TicketDTO data = new TicketDTO(); // DATA which we want to return to frontend
		data.setTicketid(ticket.getTicketid());
		data.setEventid(ticket.getEvent().getEventid());
		data.setValid(true);
		data.setTickettype(ticket.getTickettype().getName());
		data.setTickettypeid(ticket.getTickettype().getTickettypeid());
		data.setDescription(ticket.getEvent().getDescription());
		// set dataprice
		// ! API accepts manual price input (User can input manually). If not set, then use the default value from the tickettype
		if (ticketDTO.getPrice() != 0) {
			data.setPrice(ticketDTO.getPrice());
		} else {
			data.setPrice(ticket.getTickettype().getPrice());
		}

	return data; 	
	}

	
	// Get ALL tickets
	@GetMapping("/tickets")
	public List<Ticket> ticketListRest() {
		return ticketsRepo.findAll();
	}

}