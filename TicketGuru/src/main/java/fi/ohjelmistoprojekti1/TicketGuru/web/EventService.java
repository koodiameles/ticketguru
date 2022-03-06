package fi.ohjelmistoprojekti1.TicketGuru.web;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

@Service
public class EventService {
	
	private EventRepository erepository; 
	
	public EventService(EventRepository erepository) {
		this.erepository = erepository; 
	}
	
	public List<Event>getAll(){
		return erepository.findAll(); 
	}
	
	public void save(Event event) {
		Objects.requireNonNull(event); 
		erepository.save(event); 
	}
}

