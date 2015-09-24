package com.jhcm.rest.backend.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String name;

	@OneToMany
	private Collection<Permission> permissions;

	public Role() {
		super();
	}

	public Role(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}

}
