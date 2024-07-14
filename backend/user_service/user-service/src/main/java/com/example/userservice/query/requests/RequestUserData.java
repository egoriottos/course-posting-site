package com.example.userservice.query.requests;

import com.example.userservice.domain.entity.Image;
import com.example.userservice.enums.Roles;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RequestUserData {
    private String login;

    private Image profileImage;

    private String email;


    private String firstname;

    private String lastname;

    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    private Roles roles;
}
