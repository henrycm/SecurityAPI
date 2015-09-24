package com.jhcm.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhcm.rest.backend.model.User;
import com.jhcm.rest.backend.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestExampleApplication.class)
@WebIntegrationTest
public class RestExampleApplicationTests {
	private final Logger log = LoggerFactory
			.getLogger(RestExampleApplicationTests.class);

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private RestTemplate restTemplate = new TestRestTemplate();

	@Resource
	private UserRepository ur;

	@Test
	public void createUser() throws JsonProcessingException {
		Map<String, Object> requestBody = new HashMap<String, Object>();

		requestBody.put("id", null);
		requestBody.put("name", "John");
		requestBody.put("email", "henrycm@gmail.com");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		// Creating http entity object with request body and headers
		HttpEntity<String> httpEntity = new HttpEntity<String>(
				OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

		// Invoking the API
		ResponseEntity<String> apiResponse = restTemplate.postForEntity(
				"http://localhost:8888/users", httpEntity, String.class);
		assertNotNull(apiResponse);
		log.debug("{}", apiResponse);
		assertEquals(HttpStatus.CREATED, apiResponse.getStatusCode());
		// Asserting the response of the API.
		long id = 1;

		// Fetching the Book details directly from the DB to verify the API
		// succeeded
		User u = ur.findOne(id);
		assertEquals("John", u.getName());

		// Delete the data added for testing
		ur.delete(id);
	}
}
