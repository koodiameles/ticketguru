package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;



public interface TicketRepository extends CrudRepository <Ticket, Long> {

    List<Ticket> findAllByTicketid(long ticketid);
   // List<Ticket> findAllBySaleid(Sale sale);
    List<Ticket>findAll(); 

}

