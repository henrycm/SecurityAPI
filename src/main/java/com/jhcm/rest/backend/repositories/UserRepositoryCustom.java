package com.jhcm.rest.backend.repositories;

import java.util.List;

import com.jhcm.rest.backend.model.User;

public interface UserRepositoryCustom
{
    public List<User> findByRolesName( String rolename );

    User findOnlyEmail( Long id );
}
