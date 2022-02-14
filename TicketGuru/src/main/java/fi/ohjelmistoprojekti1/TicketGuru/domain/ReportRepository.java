package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface ReportRepository extends CrudRepository<Report, Long> {

    List<Report> findByName(String reportname);
    
}