package fi.ohjelmistoprojekti1.TicketGuru;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import fi.ohjelmistoprojekti1.TicketGuru.domain.EventRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerWithAuthorizationTest {
	
	  @Autowired
	  private MockMvc mockMvc;
	  @Autowired
	  private EventRepository eventRepo;
	  
	  // Not a test, only create test event
	  JSONObject testEvent() throws JSONException {
		  JSONObject event = new JSONObject();
		  event.put("description", "TestingHappen");
		  event.put("location", "TestHouse");
		  event.put("city", "Helsinki");
		  event.put("ticketcount", 50);
		  event.put("datetime", "2022-06-22T18:00:00");
		  event.put("duration", 60);
		  return event;
	  }
	  
	  // Test POST, GET and DELETE when authorization is admin 
	  @Test
	  @WithMockUser(username = "admin", password = "admin", authorities = "ADMIN")
	  void testEventIsCreatedAndDeleted() throws Exception {
		  
		  JSONObject testEvent = testEvent();
		  
		  mockMvc.perform(post("/events")
		  		.content(testEvent.toString())
		  		.contentType("application/json"))
		  		.andExpect(status().isCreated())
		  		.andExpect(content().contentType("application/json"));
		  
		  
		  String data = "$[" + (eventRepo.findAll().size()-1) + "].description";
		  
		  mockMvc.perform(get("/events"))
			  		.andExpect(status().isOk())
			  		.andExpect(content().contentType("application/json"))
			  		.andExpect(jsonPath(data).value("TestingHappen"));
		  
		  	
			long id = eventRepo.findAll().size()-1;
			  
			mockMvc.perform(delete("/events/{id}", id)
					.contentType("application/json"))
				  	.andExpect(status().isOk());		  		  
	  }
	  
	  // Test POST when authorization is user
	  @Test
	  @WithMockUser(username = "user", password = "user", authorities = "USER")
	  void testEventIsCreated() throws Exception {
		  
		  JSONObject testEvent = testEvent();
		  
		  mockMvc.perform(post("/events")
		  		.content(testEvent.toString())
		  		.contentType("application/json"))
		  		.andExpect(status().isForbidden());
	  }
}
