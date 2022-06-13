package com.example.VKR.dto.user;

import com.example.VKR.model.Role;

import java.util.List;
import java.util.Set;

public class ChangeUserRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private Set<Role> rolesId;

    public ChangeUserRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Role> getRolesId() {
        return rolesId;
    }

    public void setRolesId(Set<Role> rolesId) {
        this.rolesId = rolesId;
    }
}
