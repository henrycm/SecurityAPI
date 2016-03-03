package com.jhcm.rest.backend.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jhcm.rest.backend.model.User;
import com.jhcm.rest.backend.repositories.UserRepository;

@Component
public class UserService
{
    @Resource
    private UserRepository userRepository;

    public List<User> list()
    {
        return userRepository.findAll();
    }

    public User get( Long id )
    {
        return userRepository.findOne( id );
    }

    public User create( User user )
    {
        return userRepository.save( user );
    }

    public User update( Long id, User user )
    {
        return userRepository.save( user );
    }

    public void delete( Long id )
    {
        userRepository.delete( id );
    }
}
