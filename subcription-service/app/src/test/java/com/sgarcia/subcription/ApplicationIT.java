package com.sgarcia.subcription;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgarcia.subcription.domain.Gender;
import com.sgarcia.subcription.domain.Subcription;
import com.sgarcia.subcription.dto.SubcriptionDTO;
import com.sgarcia.subcription.service.SubcriptionNotificationService;;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class ApplicationIT {	

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private OAuth2TestHelper oAuthHelper;
	
	@MockBean
	SubcriptionNotificationService subcriptionNotificationService;
	
	@Before
	public void setup() {
		doNothing().when(subcriptionNotificationService).sendNotification(any(Subcription.class));
	}
	
	@Test
    @Order(1)   
	public void createSubcription_Unauthorized() throws Exception {
		
		SubcriptionDTO subcriptionDTO = SubcriptionDTO.builder()
				.email("test@test.com")
				.firstName("Test")
				.gender(Gender.MALE)
				.birthdate(LocalDate.now())
				.consent(true)
				.newsletterId(1L)
				.build();
		
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/api/subcriptions")
			      .content(asJsonString(subcriptionDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isUnauthorized());
		
		verify(subcriptionNotificationService, Mockito.times(0)).sendNotification(any(Subcription.class));
		
	}
	
	@Test
    @Order(2)   
	public void createSubcription_Ok() throws Exception {
		
		SubcriptionDTO subcriptionDTO = SubcriptionDTO.builder()
				.email("test@test.com")
				.firstName("Test")
				.gender(Gender.MALE)
				.birthdate(LocalDate.now())
				.consent(true)
				.newsletterId(1L)
				.build();
		
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/api/subcriptions")
                  .with(oAuthHelper.mockAccessToken("test"))
			      .content(asJsonString(subcriptionDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isCreated());
		
		verify(subcriptionNotificationService, Mockito.times(1)).sendNotification(any(Subcription.class));
		
	}
	
	@Test
    @Order(3)   
	public void createSubcription_NotUnique() throws Exception {
		
		SubcriptionDTO subcriptionDTO = SubcriptionDTO.builder()
				.email("test@test.com")
				.firstName("Test")
				.gender(Gender.MALE)
				.birthdate(LocalDate.now())
				.consent(true)
				.newsletterId(1L)
				.build();
		
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/api/subcriptions")
                  .with(oAuthHelper.mockAccessToken("test"))
			      .content(asJsonString(subcriptionDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(content().string("Error saving subcription in DB"));
		
		verify(subcriptionNotificationService, Mockito.times(0)).sendNotification(any(Subcription.class));
		
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
