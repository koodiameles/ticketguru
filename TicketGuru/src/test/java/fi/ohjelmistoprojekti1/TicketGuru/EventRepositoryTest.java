package fi.ohjelmistoprojekti1.TicketGuru;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.ohjelmistoprojekti1.TicketGuru.domain.Event;
import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
	private EventRepository eventRepo; 

    //function for more convenient date input. E.g: Date date = parseDate("2022-06-24 18:00");
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
        } catch (ParseException e) {
            return null;
        }
     }
	
    //find an existing event
    @Test
    public void findByNameShouldReturnOneEvent() {
        List<Event> event = eventRepo.findByDescriptionIgnoringCase("Sinfoniaorkesteri");
        assertThat(event).hasSize(1);
        assertThat(event.get(0).getLocation()).isEqualTo("Musiikkitalo");
    }

    //Create new event, update some properties and check that the values have been updated correctly
    @Test
    public void createEventAndUpdateTicketCount() {
        Date date = parseDate("2022-06-24 18:00");
        Event testEvent = new Event("testiNimi", "testiLokattio", "testiCity", 1000, date, 120);
        eventRepo.save(testEvent);
        assertThat(testEvent.getEventid()).isNotNull(); //id has been generated
        testEvent.setTicketcount(testEvent.getTicketcount() - 200); //200 tickets sold
        assertThat(testEvent.getTicketcount()).isEqualTo(800); // remaining tickets is correct
    }
    
}
