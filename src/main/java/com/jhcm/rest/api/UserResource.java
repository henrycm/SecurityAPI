package com.jhcm.rest.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jhcm.rest.backend.model.User;
import com.jhcm.rest.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource
{
    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll()
    {
        return userService.list();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public User get( @PathVariable Long id )
    {
        return userService.get( id );
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User create( @RequestBody User user )
    {
        return userService.create( user );
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete( @PathVariable Long id )
    {
        userService.delete( id );
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public User update( @PathVariable Long id, @RequestBody User user )
    {
        return userService.update( id, user );
    }
}
