package com.jhcm.rest.backend.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jhcm.rest.backend.model.User;

public class UserRepositoryImpl implements UserRepositoryCustom
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findByRolesName( String rolename )
    {
        return em.createQuery( "SELECT u FROM User u LEFT JOIN u.roles r WHERE r.name = :name", User.class ).setParameter( "name", rolename ).getResultList();
    }

    @Override
    public User findOnlyEmail( Long id )
    {
        return em.createQuery( "SELECT new User(u.id, u.email) FROM User u WHERE u.id = :id", User.class ).setParameter( "id", id ).getSingleResult();
    }
}
