package fi.ohjelmistoprojekti1.TicketGuru;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Sale;
import fi.ohjelmistoprojekti1.TicketGuru.domain.SaleRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Ticket;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TicketRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;
import fi.ohjelmistoprojekti1.TicketGuru.web.TicketDTO;

@SpringBootApplication
public class TicketGuruApplication {
	private static final Logger log = LoggerFactory.getLogger(TicketGuruApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TicketGuruApplication.class, args);
	}

	//function for more convenient date input. E.g: Date date = parseDate("2022-06-24 18:00");
	public static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
		} catch (ParseException e) {
			return null;
		}
	 }

	@Bean
	public CommandLineRunner DemoRunner(EventRepository eventrepository, TicketRepository ticketrepository, TickettypeRepository tickettyperepository, SaleRepository salerepository) {
		return (args) -> {

			//TEST DATA

			//EVENT TESTDATA
			log.info("save some event test data");
			Date date = parseDate("2022-06-24 18:00");
			Date date2 = parseDate("2022-08-12 15:30"); 
			Event event1 = new Event("Konsertti", "Finlandia-Talo", "Helsinki", 400, date, 90);
			Event event2 = new Event("Trio röyhkeät", "Musiikkitalo", "Helsinki", 300, date2, 120);
			Event event3 = new Event("Event3");
			eventrepository.save(event1); 
			eventrepository.save(event2);
			eventrepository.save(event3);	 

			//TICKETTYPE TESTDATA
			Tickettype tt1 = new Tickettype("Adult", 50.50, event1);
			Tickettype tt2 = new Tickettype("Child", 25.50, event1);
			Tickettype tt3 = new Tickettype("Adult", 13.50, event2);
			Tickettype tt4 = new Tickettype("Child", 5.50, event2);
			tickettyperepository.save(tt1); 
			tickettyperepository.save(tt2);
			tickettyperepository.save(tt3); 
			tickettyperepository.save(tt4);
	
			//SALE TESTDATA
			Date dateNow = new Date();
			Date date3 = parseDate("2022-07-22 12:37"); 
			Sale testsale1 = new Sale(dateNow);
			salerepository.save(testsale1);
			Sale testsale2 = new Sale(date3);
			salerepository.save(testsale2);

			//TICKET TESTDATA
			Ticket ticket1 = new Ticket(true, tt1.getPrice(), event1, testsale1, tt1);
			ticketrepository.save(ticket1);

			//TICKETDTO with entities TESTDATA
			TicketDTO ticketDTO = new TicketDTO(tt1, event1);
			log.info(ticketDTO.toString());

			//LOG DATA IN TERMINAL
			log.info("fetch all events");
			for (Event event : eventrepository.findAll()) {
				log.info(event.toString());
			}

			log.info("fetch all tickets");
			for (Ticket ticket : ticketrepository.findAll()) {
				log.info(ticket.toString());
			}
			
			log.info("fetch all sales");
			for (Sale sale : salerepository.findAll()) {
				log.info(sale.toString());
			}
	

		};
	}

}
