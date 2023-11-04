package com.sales.sales_system.dto.user;

import com.sales.sales_system.entity.Customer;
import com.sales.sales_system.entity.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class UserGetDTO
{
    private Long id;
    private String name;
    private Long document;
    private String user;
    private Long phone;
    private Role role;

    public UserGetDTO() { }
    public UserGetDTO(Long id, String name, Long phone, Long document, String user, Role role)
    {
        this.id = id;
        this.name = name;
        this.document = document;
        this.user = user;
        this.role = role;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static List<UserGetDTO> convertListEntityToListDTO(List<Customer> customers)
    {
        List<UserGetDTO> userDtoList = new ArrayList<>();

        for (Customer user : customers)
        {
            UserGetDTO userDto = new UserGetDTO();

            userDto.setId(user.getId());
            userDto.setUser(user.getUser());
            userDto.setName(user.getName());
            userDto.setDocument(user.getDocument());
            userDto.setPhone(user.getPhone());
            userDto.setRole(user.getRole());

            userDtoList.add(userDto);
        }

        return userDtoList;
    }
}
