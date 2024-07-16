package com.example.userservice.commands;

import com.example.userservice.domain.entity.Image;
import com.example.userservice.domain.entity.enums.Roles;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CreateUserCommand {
    private String login;

    private String email;

    private String firstname;

    private String lastname;

    private Image profileImage;

    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    private Roles roles;
}
