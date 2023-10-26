package com.sales.sales_system.dto.user;

import com.sales.sales_system.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UserStoreDTO
{
    private String name;
    private Long document;
    private int phone;
    private String password;
    private String user;
    private Long role_id;

    public UserStoreDTO(String name, Long document, int phone, String password, String user, Long role_id) {
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.password = password;
        this.user = user;
        this.role_id = role_id;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}
