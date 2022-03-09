package fi.ohjelmistoprojekti1.TicketGuru.web;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.ohjelmistoprojekti1.TicketGuru.domain.EmployeeRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Sale;
import fi.ohjelmistoprojekti1.TicketGuru.domain.SaleRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

@RestController
public class SaleController {

    @Autowired
	private SaleRepository salerepository;
    
    @Autowired
	private EmployeeRepository employeeepository;
	
	@Autowired
	private TickettypeRepository tickettyperepository;


	// Get one sale
	@GetMapping("/sale/{id}")
	public Optional<Sale> findSaleRest(@PathVariable("id") Long saleid) {
		return salerepository.findById(saleid);
	}
	
	// Get all sales
	@GetMapping("/sales")
	public List<Sale> saleListRest() {
		return salerepository.findAll();
	}
	
	// Add (POST) a new sale
    @PostMapping("/sales")
    public Sale addSale(@RequestBody Sale sale) {
        salerepository.save(sale);
        return sale;
    }

}
