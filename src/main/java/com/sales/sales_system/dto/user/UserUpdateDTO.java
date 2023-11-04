package com.sales.sales_system.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserUpdateDTO
{
    private Long id;
    @NotBlank
    private String name;
    @Min(value = 10_000_000)
    private Long document;
    private Long phone;
    private String password;
    private String confirmPassword;
    private String user;
    private Integer role_id;

    public UserUpdateDTO() { }

    public UserUpdateDTO(Long id, String name, Long document, Long phone, String password, String confirmPassword, String user, Integer role_id) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.user = user;
        this.role_id = role_id;
    }

    public UserUpdateDTO(String name, Long document, Long phone, String password, String confirmPassword, String user, Integer role_id) {
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.user = user;
        this.role_id = role_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
}
