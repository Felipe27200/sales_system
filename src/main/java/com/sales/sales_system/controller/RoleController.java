package com.sales.sales_system.controller;

import com.sales.sales_system.entity.Role;
import com.sales.sales_system.response.Response;
import com.sales.sales_system.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${apiPrefix}")
public class RoleController
{
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService)
    {
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public Response<?> saveRole(@Valid @RequestBody Role role)
    {
        Role newRole = this.roleService.saveRole(role);

        return new Response<>("Role '" + newRole.getName() + "' created successfully",
    "successful");
    }

    @GetMapping("/roles")
    public Response<?> findAll()
    {
        List<Role> roles = this.roleService.findAll();

        return new Response<>(roles, "successful");
    }

    @GetMapping("/roles/{id}")
    public Response<?> findById(@PathVariable Integer id)
    {
        return new Response<>(this.roleService.findById(id), "successful");
    }

    @PutMapping("/roles/{id}")
    public Response<?> updateRole(@Valid @RequestBody Role role, @PathVariable Integer id)
    {
        Role updateRole = this.roleService.updateRole(role, id);

        return new Response<>(updateRole, "successful");
    }

    @DeleteMapping("/roles/{id}")
    public Response<?> deleteRole(@PathVariable Integer id)
    {
        Role deletedRole = this.roleService.deleteRole(id);

        return new Response<>("The role '" + deletedRole.getName() + "' deleted successfully", "successful");
    }
}
