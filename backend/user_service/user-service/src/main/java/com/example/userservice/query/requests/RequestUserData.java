package com.example.userservice.query.requests;

import com.example.userservice.enums.Roles;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class RequestUserData {
    private String login;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String phone;

    private Date dateOfBirth;

    private Roles roles;
}
