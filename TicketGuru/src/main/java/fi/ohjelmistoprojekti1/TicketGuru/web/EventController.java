package fi.ohjelmistoprojekti1.TicketGuru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TicketRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository repository; 
	
	@Autowired
	private TicketRepository trepository; 
	
	@Autowired
	private TickettypeRepository ttrepository; 

}
