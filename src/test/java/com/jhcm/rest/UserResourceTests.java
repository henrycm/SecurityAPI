package com.jhcm.rest;

import static com.jhcm.rest.TestUtil.buildUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhcm.rest.backend.model.User;
import com.jhcm.rest.backend.repositories.RoleRepository;
import com.jhcm.rest.backend.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SecurityApplication.class)
@WebIntegrationTest("server.port:8888")
@Transactional
public class UserResourceTests
{
    private final static Logger LOG = LoggerFactory.getLogger( UserResourceTests.class );

    private RestTemplate restTemplate = new TestRestTemplate();

    private final HttpHeaders requestHeaders = new HttpHeaders();
    private final String userEndpoint = "http://localhost:8888/users/";

    @Resource
    private UserRepository ur;

    @Resource
    private RoleRepository rr;

    @Before
    public void setUp()
    {
        requestHeaders.setContentType( MediaType.APPLICATION_JSON );
    }

    @Test
    public void createUser() throws JsonProcessingException
    {
        User u = createUser( buildUser() );
        long id = 1;
        u = getUser( id );
        assertEquals( "John", u.getName() );
        u.setEmail( "henrycm@gmail.com.ca" );
        update( u );
        u = getUser( u.getId() );
        assertEquals( new Long( 2L ), u.getVersion() );
        queryUser();
        deleteUser( u.getId() );
    }

    @Test
    public void testAuditing() throws JsonProcessingException
    {
        User u = buildUser();
        u = ur.save( u );
        u.setEmail( "update@gmail.com.ca" );
        u = ur.save( u );
        u = ur.getOne( u.getId() );
        LOG.debug( "User: [{}]", new ObjectMapper().writeValueAsString( u ) );
        assertNotNull( u.getCreatedBy() );
        assertNotNull( u.getModifiedBy() );
        assertNotNull( u.getCreationTime() );
        assertNotNull( u.getModificationTime() );
    }

    public void queryUser()
    {
        final HashMap<String, String> urlVariables = new HashMap<String, String>();
        urlVariables.put( "page", "0" );
        ResponseEntity<String> apiResponse = restTemplate.getForEntity( userEndpoint + "/1", String.class );
        assertNotNull( apiResponse );
        LOG.debug( "{}", apiResponse );
    }

    public User createUser( final User u )
    {
        ResponseEntity<User> ru = this.restTemplate.postForEntity( userEndpoint,            u, User.class );
        assertEquals( HttpStatus.CREATED, ru.getStatusCode() );
        return ru.getBody();
    }

    private User getUser( long id )
    {
        ResponseEntity<User> ru = restTemplate.getForEntity( userEndpoint + "{id}", User.class, id );
        assertEquals( HttpStatus.OK, ru.getStatusCode() );
        return ru.getBody();
    }

    private void update( User u )
    {
        this.restTemplate.put( userEndpoint + "{id}", u, u.getId() );
    }

    public void deleteUser( final long id )
    {
        this.restTemplate.delete( userEndpoint + "{id}", id );
    }

}
