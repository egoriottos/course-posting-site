package com.example.userservice.responses;

import com.example.userservice.domain.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {

    private Long id;

    private User owner;

    private List<Integer> coursesId;

    private List<Integer>quizResultsId;
}
