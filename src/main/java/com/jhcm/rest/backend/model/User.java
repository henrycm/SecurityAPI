package com.jhcm.rest.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User extends BaseEntity
{
    public User( Long id, String email )
    {
        this.id = id;
        this.email = email;
    }

    public User()
    {
    }

    @Id
    @NotNull
    private Long id;

    @NotEmpty(message = "{user.email.error}")
    private String email;
    @NotEmpty
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles( List<Role> roles )
    {
        this.roles = roles;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }
}
