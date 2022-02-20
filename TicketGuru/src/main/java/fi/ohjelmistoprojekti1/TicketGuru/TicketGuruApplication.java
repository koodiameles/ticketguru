package fi.ohjelmistoprojekti1.TicketGuru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TicketRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

@SpringBootApplication
public class TicketGuruApplication {
	private static final Logger log = LoggerFactory.getLogger(TicketGuruApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TicketGuruApplication.class, args);
	}

	@Bean
	public CommandLineRunner DemoRunner(EventRepository eventrepository, TicketRepository ticketrepository, TickettypeRepository tickettyperepository) {
		return (args) -> {

			//testidataa
			log.info("save some event test data");
			eventrepository.save(new Event("Event1")); //kaikki arvot null paitsi description
			eventrepository.save(new Event("Event2"));
			eventrepository.save(new Event("Event3"));	
			
			log.info("fetch all events");
			for (Event event : eventrepository.findAll()) {
				log.info(event.toString());
			}

		};
	}

}
