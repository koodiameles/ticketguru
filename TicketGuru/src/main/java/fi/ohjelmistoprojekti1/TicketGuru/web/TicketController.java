package fi.ohjelmistoprojekti1.TicketGuru.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
import net.minidev.json.JSONObject;

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
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<TicketDTO> addTicketToSale(@PathVariable long id, @RequestBody TicketDTO ticketDTO,
			BindingResult bindingresult) {

		Optional<Sale> saleResult = salesRepo.findById(id); // Sale for which the ticket will be added
		if (!saleResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sale id " + id + " not valid");
		} // if the saleid doesn't exist => error

		Optional<Event> eventResult = eventsRepo.findById(ticketDTO.getEventid()); // Event in question
		if (!eventResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Event id " + ticketDTO.getEventid() + " not valid");
		} // if the eventid doesn't exist => error

		Optional<Tickettype> tickettypeResult = tickettypesRepo.findById(ticketDTO.getTickettypeid()); // Tickettype in
		// question
		if (!tickettypeResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Tickettype id " + ticketDTO.getTickettypeid() + " not valid");
		} // if the tickettypeid doesn't exist => error

		Sale sale = saleResult.get(); // get the sale in question
		Event event = eventResult.get(); // get the event in question
		Tickettype tickettype = tickettypeResult.get(); // get the event in question

		// ADDING TICKET TO DATABASE
		Ticket ticket = new Ticket(true, event, sale, tickettype); // generate ticket (is valid?, which event?, which
		// sale?, which tickettype?)
		// set ticketprice
		// ! API accepts manual price input (User can input manually). If not set, then
		// use the default value from the tickettype
		if (ticketDTO.getPrice() != 0) {
			ticket.setTicketprice(ticketDTO.getPrice());
		} else {
			ticket.setTicketprice(ticket.getTickettype().getPrice());
		}

		if (bindingresult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					bindingresult.getFieldError().getDefaultMessage());
		}
		ticketsRepo.save(ticket); // save the ticket to the ticketrepository

		// DATA WHICH WILL BE RETURNED
		TicketDTO data = new TicketDTO(); // DATA which we want to return to frontend
		data.setTicketid(ticket.getTicketid());
		data.setEventid(ticket.getEvent().getEventid());
		data.setValid(true);
		data.setTicketcode(ticket.getTicketcode());
		data.setTickettype(ticket.getTickettype().getName());
		data.setUseddatetime(ticket.getUseddatetime());
		data.setTickettypeid(ticket.getTickettype().getTickettypeid());
		data.setDescription(ticket.getEvent().getDescription());
		// set dataprice
		// ! API accepts manual price input (User can input manually). If not set, then
		// use the default value from the tickettype

		// if (ticketDTO.getPrice() != 0 && ticketDTO.getPrice() > 0) {
		// data.setPrice(ticketDTO.getPrice());
		// }
		// else {
		// data.setPrice(ticket.getTickettype().getPrice());
		// }

		if (ticketDTO.getPrice() != 0 && ticketDTO.getPrice() > 0) {
			data.setPrice(ticketDTO.getPrice());
		}
		if (ticketDTO.getPrice() == 0) {
			data.setPrice(ticket.getTickettype().getPrice());
		}
		if (ticketDTO.getPrice() < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Price must be a positive number (e.g 5.50) or null. (If null, price is set automatically according to tickettype)");
		}

		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

	// Get ALL tickets
	@GetMapping("/tickets")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Ticket>> getAllTickets(@RequestParam(name= "code", required = false) String ticketcode) {
		List<Ticket> list = (List<Ticket>) ticketsRepo.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// GET ticket by id
	@GetMapping("/tickets/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public Optional<Ticket> findTicket(@PathVariable("id") Long ticketid) {
		Optional<Ticket> ticket = ticketsRepo.findById(ticketid);
		if (!ticket.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket id " + ticketid + " not found");
		} // if event id doesn't exist => error
		return ticket;
	}

	// GET ticket by ticket code
	@GetMapping(value = "/tickets", params = { "code" })
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public Ticket findTicketByCode(@RequestParam("code") String ticketcode) {
		Ticket ticket = ticketsRepo.findByTicketcode(ticketcode);

		if (ticket == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket code " + ticketcode + " not found");
		} // if ticketcode doesn't exist => error
		return ticket;
	}

	// PATCH mark ticket as used by ticketcode
	@PatchMapping("/tickets")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public JSONObject useTicketByCode(@RequestParam("code") String ticketcode) {
		Ticket ticket = ticketsRepo.findByTicketcode(ticketcode);

		if (ticket == null) { // if ticketcode doesn't exist => error
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket code " + ticketcode + " not found");
		} else if (!ticket.getValid()) { //if ticketcode is not valid => error
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket with ticket code " + ticketcode + " has already been used");
		}
		ticket.setUseddatetime(new Date());
		ticket.setValid(false);
		ticketsRepo.save(ticket);

		JSONObject response = new JSONObject();
		response.put("used", ticket.getUseddatetime());
		return response;
	}

}