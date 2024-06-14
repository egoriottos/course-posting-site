package com.example.userservice.responses;

import com.example.userservice.domain.entity.User;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class TeacherResponse {
    private Long id;

    private UserResponse owner;

    private Float rating;

    private List<Integer> coursesId;
}
