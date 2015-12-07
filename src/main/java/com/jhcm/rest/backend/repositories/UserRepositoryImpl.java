package com.jhcm.rest.backend.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.jhcm.rest.backend.model.Role;
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
    public List<User> findByRolesNameQuery( String rolename )
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery( User.class );
        Root<User> user = query.from( User.class );
        Join<User, Role> roles = user.join( "roles" );
        query.where( cb.equal( roles.get( "name" ), rolename ) );
        return em.createQuery( query ).getResultList();
    }

    @Override
    public User findOnlyEmail( Long id )
    {
        return em.createQuery( "SELECT new User(u.id, u.email) FROM User u WHERE u.id = :id", User.class ).setParameter( "id", id ).getSingleResult();
    }
}
