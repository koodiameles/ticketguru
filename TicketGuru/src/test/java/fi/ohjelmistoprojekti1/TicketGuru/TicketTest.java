package fi.ohjelmistoprojekti1.TicketGuru;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Employee;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EmployeeRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.RoleRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Sale;
import fi.ohjelmistoprojekti1.TicketGuru.domain.SaleRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Ticket;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TicketRepository;
import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

@DataJpaTest
public class TicketTest {

	@Autowired
	private TicketRepository TRepo;

	@Autowired
	private EventRepository ERepo;

	@Autowired
	private TickettypeRepository TypeRepo;

	public static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	@Test
	public void createNewTicket() {
		Date dateTest = parseDate("2022-06-24 18:00");

		Event eventTest = new Event("IlosaariRock", "Laulurinne", "Joensuu", 300, dateTest, 100);
		Tickettype typeTest = new Tickettype("Lapsi", 5.50, eventTest);
		Ticket testTicket = new Ticket(true, eventTest, typeTest);
		TRepo.save(testTicket);
		ERepo.save(eventTest);
		TypeRepo.save(typeTest);

		assertThat(testTicket.getValid()).isTrue();
		assertThat(testTicket.getTicketid()).isNotNull();
		assertThat(testTicket.getTicketprice()).isNotNull();
		assertThat(testTicket.getTicketcode()).isNotNull();
		assertThat(testTicket.getTickettype()).isNotNull();

	}

}
