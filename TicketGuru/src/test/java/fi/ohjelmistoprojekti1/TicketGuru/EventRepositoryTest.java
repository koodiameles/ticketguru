package fi.ohjelmistoprojekti1.TicketGuru;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;

public class EventRepositoryTest {

    //work in progress -jj

    /* @Autowired
	private EventRepository eventRepo;  */
	
    /* @Test
    public void findByNameShouldReturnOneEvent() {
        List<Event> event = eventRepo.findByDescriptionIgnoringCase("Sinfoniaorkesteri");
        assertThat(event).hasSize(1);
        assertThat(event.get(0).getLocation()).isEqualTo("Musiikkitalo");
    } */
    
}
