package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SaleRepository extends CrudRepository<Sale, Long> {

    List<Sale> findBySaleid(Long saleid);
    List<Sale>findAll(); 
}