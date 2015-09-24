package com.jhcm.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Before;
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
import com.jhcm.rest.backend.model.Role;
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

	private final HttpHeaders requestHeaders = new HttpHeaders();
	private final String userEndpoint = "http://localhost:8888/users";

	@Resource
	private UserRepository ur;

	@Before
	public void setUp() {
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	}

	@Test
	public void createUser() throws JsonProcessingException {

		// Creating http entity object with request body and headers
		HttpEntity<String> httpEntity = new HttpEntity<String>(
				OBJECT_MAPPER.writeValueAsString(buildUser()), requestHeaders);

		// Invoking the API
		ResponseEntity<String> apiResponse = restTemplate.postForEntity(
				userEndpoint, httpEntity, String.class);
		assertNotNull(apiResponse);
		log.debug("{}", apiResponse);
		assertEquals(HttpStatus.CREATED, apiResponse.getStatusCode());
		// Asserting the response of the API.
		long id = 1;

		// Fetching the Book details directly from the DB to verify the API
		// succeeded
		User u = ur.findOne(id);
		assertEquals("John", u.getName());

		queryUser();
		// Delete the data added for testing
		ur.delete(id);
	}

	public void queryUser() {
		final HashMap<String, String> urlVariables = new HashMap<String, String>();
		urlVariables.put("page", "0");
		ResponseEntity<String> apiResponse = restTemplate.getForEntity(
				userEndpoint + "/1", String.class);
		assertNotNull(apiResponse);
		log.debug("{}", apiResponse);
	}

	private User buildUser() {
		User u = new User();
		u.setId(1L);
		u.setName("John");
		u.setEmail("henrycm@gmail.com");
		u.setRoles(Arrays.asList(new Role[] { new Role("r1", "Role1") }));
		return u;
	}
}
