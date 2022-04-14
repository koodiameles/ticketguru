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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Employee;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EmployeeRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Sale;
import fi.ohjelmistoprojekti1.TicketGuru.domain.SaleRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

@RestController
public class SaleController {

	@Autowired
	private SaleRepository salerepository;

	@Autowired
	private EmployeeRepository employeerepository;

	@Autowired
	private TickettypeRepository tickettyperepository;

	// Get one sale
	@GetMapping("/sales/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Optional<Sale> findSaleRest(@PathVariable("id") Long saleid) {
		Optional<Sale> saleResult = salerepository.findById(saleid);
		if (!saleResult.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale id " + saleid + " not found");
		}
		return saleResult;
	}

	// Get all sales
	@GetMapping("/sales")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Sale>> getAllSales() {
		List<Sale> list = salerepository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// Add (POST) a new sale
	@PostMapping("/sales")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public Sale addSale(@Valid @RequestBody Sale sale, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingresult.getFieldError().getDefaultMessage());
		}
		sale.setDatetime(new Date());
		salerepository.save(sale);
		return sale;
	}

}
