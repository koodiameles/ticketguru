package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface TickettypeRepository extends CrudRepository<Tickettype, Long> {

    List<Tickettype> findByName(String tickettype);
    
}