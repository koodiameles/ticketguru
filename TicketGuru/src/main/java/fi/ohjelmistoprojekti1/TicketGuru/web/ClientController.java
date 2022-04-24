package fi.ohjelmistoprojekti1.TicketGuru.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ClientController {

    // GET INDEX
	@GetMapping(value={"/index", "/"})
	public String getindex () {
		return "index";
	}

	 // GET SELLTICKETS - 
	 @GetMapping(value={"/selltickets"})
	 public String getSellTickets () {
		 return "selltickets";
	 }
    
}
