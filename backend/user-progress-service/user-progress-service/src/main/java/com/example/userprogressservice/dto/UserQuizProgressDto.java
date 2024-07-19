package com.example.userprogressservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserQuizProgressDto {
    private Long id;
    private Long userId;
    private Long quizId;
    private Double score;
    private Boolean completed;
    private Date dateTaken;//дата когда тест был пройден
}
