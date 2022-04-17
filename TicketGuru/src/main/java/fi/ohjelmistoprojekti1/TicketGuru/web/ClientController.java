package fi.ohjelmistoprojekti1.TicketGuru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fi.ohjelmistoprojekti1.TicketGuru.domain.TicketRepository;

import org.springframework.ui.Model;

@Controller
public class ClientController {

    // GET INDEX
	@GetMapping(value={"/index", "/"})
	public String getindex () {
		return "index";
	}

	 // GET SELLTICKETS - 
	 @GetMapping(value={"/selltickets"})
	 public String getSellTickets (Model model) {
		 return "selltickets";
	 }
    
}
