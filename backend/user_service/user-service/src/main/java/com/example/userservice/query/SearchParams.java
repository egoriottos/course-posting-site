package com.example.userservice.query;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
@Builder
public class SearchParams {
    private Long id;

    private String login;

    private String email;

    private String firstname;

    private String lastname;

    private String phone;

    private Date createdAt;

    private Date deletedAt;

    private Date dateOfBirth;

    private Integer limit = 15;

    private Integer offset = 0;
}
