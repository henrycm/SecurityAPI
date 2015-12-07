package com.jhcm.rest.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

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

    @Version
    @NotNull
    private Long version = 1L;

    @NotEmpty(message = "{user.email.error}")
    private String email;
    @NotEmpty
    private String name;

    @LastModifiedDate
    private Date lastUpdate;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;

    public Long getVersion()
    {
        return version;
    }

    public void setVersion( Long version )
    {
        this.version = version;
    }

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

    public Date getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate( Date lastUpdate )
    {
        this.lastUpdate = lastUpdate;
    }
}
