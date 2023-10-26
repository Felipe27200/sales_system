package com.sales.sales_system.repository;

import com.sales.sales_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer>
{
    public Role findRoleByName(String name);

    @Query(
        value = "SELECT * FROM roles WHERE name LIKE %?1%",
        nativeQuery = true
    )
    public List<Role> findRolesByNameCoincidence(String name);
}
