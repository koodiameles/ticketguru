package fi.ohjelmistoprojekti1.TicketGuru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


import fi.ohjelmistoprojekti1.TicketGuru.domain.Employee;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EmployeeRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Role;
import fi.ohjelmistoprojekti1.TicketGuru.domain.RoleRepository;
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

@Profile("heroku")	 
@Bean
	public CommandLineRunner DemoRunner(EventRepository eventrepository, RoleRepository rolerepository, TicketRepository ticketrepository, TickettypeRepository tickettyperepository, SaleRepository salerepository, EmployeeRepository emprepository) {
		return (args) -> {

			//TEST DATA
			
			//EVENT TESTDATA
			 log.info("save some event test data");
			 Date date = parseDate("2022-06-24 18:00");
			 Date date2 = parseDate("2022-08-12 15:30");
			 Date date4 = parseDate("2022-10-15 17:30");
			 Event event1 = new Event("Konsertti", "Finlandia-Talo", "Helsinki", 400, date, 90);
			 Event event2 = new Event("Trio röyhkeät", "Musiikkitalo", "Helsinki", 300, date2, 120);
			 Event event3 = new Event("Sinfoniaorkesteri",  "Musiikkitalo", "Tampere", 300, date4, 100);
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
			Ticket ticket2 = new Ticket(true, tt2.getPrice(), event2, testsale2, tt2);
			ticketrepository.save(ticket1);
			ticketrepository.save(ticket2);

			//TICKETDTO with entities TESTDATA
			TicketDTO ticketDTO = new TicketDTO(tt1, event1);
			log.info(ticketDTO.toString());

			//ROLE TESTDATA
			Role roleuser = new Role("USER");
			Role roleadmin = new Role("ADMIN");
			rolerepository.save(roleuser);
			rolerepository.save(roleadmin);

			//EMPLOYEE TESTDATA
			//firstname, lastname, username, password, role
			Employee user = new Employee("Liisa", "Ihmemaa", "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", roleuser);
			Employee admin = new Employee("cpt Jaakko", "Varpunen", "admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", roleadmin);
			emprepository.save(user);
			emprepository.save(admin);

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
			log.info("fetch all employees");
			for (Employee employee : emprepository.findAll()) {
			log.info(employee.toString());
			}
		};
	}

}
