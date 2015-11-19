package com.jhcm.rest.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhcm.rest.backend.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>
{

}
