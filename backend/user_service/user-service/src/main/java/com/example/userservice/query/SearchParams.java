package com.example.userservice.query;

import com.example.userservice.enums.Roles;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

//@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchParams {
    public SearchParams(@JsonProperty("id")Long id, @JsonProperty("login") String login, @JsonProperty("email") String email,
                        @JsonProperty("firstname") String firstname, @JsonProperty("lastname")String lastname,@JsonProperty("phone") String phone,
                        @JsonProperty("createdAt")Date createdAt, @JsonProperty("deletedAt")Date deletedAt, @JsonProperty("dateOfBirth")Date dateOfBirth,
                        @JsonProperty("limit")Integer limit, @JsonProperty("offset")Integer offset, @JsonProperty("roles")Roles roles) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.dateOfBirth = dateOfBirth;
        this.limit = limit;
        this.offset = offset;
        this.roles = roles;
    }

    private Long id;

    private String login;

    private String email;

    private String firstname;

    private String lastname;

    private String phone;

    private Date createdAt;

    private Date deletedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    private Roles roles;

    private Integer limit = 15;

    private Integer offset = 0;
}
