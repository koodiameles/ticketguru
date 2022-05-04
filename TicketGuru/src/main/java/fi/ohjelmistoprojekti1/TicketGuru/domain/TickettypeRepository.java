package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype; 


public interface TickettypeRepository extends CrudRepository<Tickettype, Long> {

    
    List<Tickettype>findAll(); 
    List<Tickettype> findByName(String name);
    List<Tickettype> findByEvent(Event event); 
    
    
}