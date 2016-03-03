package com.jhcm.rest;

import static com.jhcm.rest.TestUtil.buildRole;
import static com.jhcm.rest.TestUtil.buildUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jhcm.rest.backend.model.Role;
import com.jhcm.rest.backend.model.User;
import com.jhcm.rest.backend.repositories.RoleRepository;
import com.jhcm.rest.backend.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SecurityApplication.class)
@IntegrationTest
@Transactional
public class TestRepository
{
    @Resource
    private UserRepository ur;
    @Resource
    private RoleRepository rr;

    @Test
    public void testUpdate()
    {
        User u = buildUser();
        ur.save( u );
        u.setEmail( "update@gmail.com.ca" );
        u = ur.saveAndFlush( u );
        u = ur.getOne( u.getId() );
        assertNotNull( "LastUpdate can't be null", u.getModificationTime() );
    }

    @Test
    public void testSearchByRole()
    {
        Role r = buildRole();
        rr.save( r );
        User u = buildUser();
        u.setRoles( Arrays.asList( new Role[] { r } ) );
        ur.save( u );

        List<User> list = ur.findByRolesName( "Role1" );
        assertNotNull( list );
        assertEquals( 1, list.size() );
    }

    @Test
    public void testQueryApiJoin()
    {
        Role r = buildRole();
        rr.save( r );
        User u = buildUser();
        u.setRoles( Arrays.asList( new Role[] { r } ) );
        ur.save( u );

        List<User> list = ur.findByRolesNameQuery( "Role1" );
        assertNotNull( list );
        assertEquals( 1, list.size() );
    }

    @Test
    public void testSelectOnlyField()
    {
        User u = buildUser();
        Role r = buildRole();
        rr.save( r );

        u.setRoles( Arrays.asList( new Role[] { r } ) );
        ur.save( u );

        User result = ur.findOnlyEmail( u.getId() );
        assertNotNull( result );
        assertEquals( u.getEmail(), result.getEmail() );
        assertNull( result.getName() );
    }

    @Test
    public void testVersion()
    {
        User u = buildUser();
        ur.save( u );
        u.setEmail( "update@gmail.com.ca" );
        u = ur.saveAndFlush( u );
        u = ur.getOne( u.getId() );
        assertEquals( new Long( 2 ), u.getVersion() );
    }

    @Test
    public void testAuditing()
    {
        User u = buildUser();
        ur.save( u );
        u.setEmail( "update@gmail.com.ca" );
        u = ur.saveAndFlush( u );
        u = ur.getOne( u.getId() );

    }
}
