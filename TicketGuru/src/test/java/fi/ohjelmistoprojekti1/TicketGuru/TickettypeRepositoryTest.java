package fi.ohjelmistoprojekti1.TicketGuru;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Tickettype;
import fi.ohjelmistoprojekti1.TicketGuru.domain.TickettypeRepository;

@DataJpaTest
public class TickettypeRepositoryTest {
	
	@Autowired
	private TickettypeRepository tickettypeRepo;
	
	@Test
	public void findByNameAndCount() {
		List<Tickettype> tickettype = tickettypeRepo.findByName("Adult");
		assertThat(tickettype).hasSize(2);
	}
	
	@Test
	public void createNewTickettype() {
		Tickettype tickettype = new Tickettype("Student", 30.00);
		tickettypeRepo.save(tickettype);
		assertNotNull(tickettype.getTickettypeid());
	}
}
