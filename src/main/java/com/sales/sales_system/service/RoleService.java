package com.sales.sales_system.service;

import com.sales.sales_system.entity.Role;
import com.sales.sales_system.error_handling.db_errors.DuplicateConstraintException;
import com.sales.sales_system.error_handling.db_errors.NotFoundException;
import com.sales.sales_system.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService
{
    private RoleRepository roleRepository;

    @Autowired
    public RoleService (RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role saveRole(Role role)
    {
        this.isUniqueName(role.getName());

        Role newRole = this.roleRepository.save(role);

        return newRole;
    }

    @Transactional
    public Role updateRole(Role role, Integer id)
    {
        Role roleBD = this.findById(id);
        this.isUniqueName(role.getName(), id);

        roleBD.setName(role.getName());

        return this.roleRepository.save(roleBD);
    }

    public List<Role> findAll()
    {
        return this.roleRepository.findAll();
    }

    public Role findById(Integer id)
    {
        Optional<Role> role = this.roleRepository.findById(id);

        if (role.isEmpty())
            throw new NotFoundException("Role with the id: '" + id + "' NOT FOUND");
        else
            return role.get();
    }

    public Role findById(String name)
    {
        Optional<Role> role = Optional.ofNullable(this.roleRepository.findRoleByName(name));

        if (role.isEmpty())
            throw new NotFoundException("Role with name: '" + name + "' NOT FOUND");
        else
            return role.get();
    }

    @Transactional
    public Role deleteRole(Integer id)
    {
        Role role = this.findById(id);

        this.roleRepository.deleteById(role.getId());

        return role;
    }

    public void isUniqueName(String name)
    {
        Role role = this.roleRepository.findRoleByName(name);

        if (role != null)
            throw new DuplicateConstraintException("The name '" + name + "' already exists.");
    }

    public void isUniqueName(String name, Integer id)
    {
        Role role = this.roleRepository.findRoleByName(name);

        if (role != null && role.getId().equals(id))
            throw new DuplicateConstraintException("The name '" + name + "' already exists.");
    }
}
