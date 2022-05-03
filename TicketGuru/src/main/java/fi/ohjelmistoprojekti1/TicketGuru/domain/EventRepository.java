package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;



public interface EventRepository extends CrudRepository <Event, Long> {

    // Search (ignoring case)
    List<Event> findByDescriptionIgnoringCase(String description);
    List<Event>findAll(); 
    
    
}
