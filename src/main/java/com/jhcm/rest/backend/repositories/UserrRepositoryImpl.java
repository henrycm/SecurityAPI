package com.jhcm.rest.backend.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jhcm.rest.backend.model.User;

public class UserrRepositoryImpl implements UserRepositoryCustom
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findByRolesName( String rolename )
    {
        return em.createQuery( "SELECT u FROM User u LEFT JOIN u.roles r WHERE r.name = :name", User.class ).setParameter( "name", rolename ).getResultList();
    }
}
