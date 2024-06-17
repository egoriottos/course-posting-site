package com.example.userservice.responses;

import com.example.userservice.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String login;

    private String email;

    private String firstname;

    private String lastname;

    private String phone;

    private Date dateOfBirth;

    private Date createdAt;

    private Roles roles;
}
