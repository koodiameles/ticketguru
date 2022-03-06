package fi.ohjelmistoprojekti1.TicketGuru;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TicketRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

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
	public CommandLineRunner DemoRunner(EventRepository eventrepository, TicketRepository ticketrepository, TickettypeRepository tickettyperepository) {
		return (args) -> {

			//testidataa
			Date date = parseDate("2022-06-24 18:00");
			Date date2 = parseDate("2022-08-12 15:30"); 
		
			tickettyperepository.save(new Tickettype("Adult", 50.50)); 
			tickettyperepository.save(new Tickettype("Child", 25.50)); 

			log.info("save some event test data");
			eventrepository.save(new Event("Konsertti", "Finlandia-Talo", "Helsinki", 400, date, 90)); 
			eventrepository.save(new Event("Trio röyhkeät", "Musiikkitalo", "Helsinki", 300, date2, 120));
			eventrepository.save(new Event("Event3"));	 
	
			
			log.info("fetch all events");
			for (Event event : eventrepository.findAll()) {
				log.info(event.toString());
			}

		};
	}

}
