package com.jhcm.rest;

import com.jhcm.rest.backend.model.Role;
import com.jhcm.rest.backend.model.User;

public class TestUtil
{

    public static User buildUser()
    {
        User u = new User();
        u.setId( 1L );
        u.setName( "John" );
        u.setEmail( "henrycm@gmail.com" );
        // u.setRoles(Arrays.asList(new Role[] { new Role("r1", "Role1") }));
        return u;
    }

    public static Role buildRole()
    {
        Role r = new Role();
        r.setId( "role1" );
        r.setName( "Role1" );
        return r;
    }
}
