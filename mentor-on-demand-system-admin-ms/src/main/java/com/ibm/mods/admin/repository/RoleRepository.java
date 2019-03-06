package com.ibm.mods.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.mods.admin.security.model.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query("SELECT r FROM Role r WHERE UPPER(r.name) = UPPER(:roleName)")
	Role getRoleByName(@Param("roleName") String roleName);
}
