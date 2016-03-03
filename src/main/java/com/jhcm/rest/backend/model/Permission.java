package com.jhcm.rest.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permission extends BaseEntity
{
    @Id
    private String id;
    private String name;

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

}
