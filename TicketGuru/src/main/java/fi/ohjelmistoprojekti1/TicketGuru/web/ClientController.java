package fi.ohjelmistoprojekti1.TicketGuru.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    // GET INDEX - SHOW MAIN PAGE
	@GetMapping(value={"/index", "/"})
	public String getindex () {
		// System.out.println("navigate to index");
		return "index";
	}
    
}
