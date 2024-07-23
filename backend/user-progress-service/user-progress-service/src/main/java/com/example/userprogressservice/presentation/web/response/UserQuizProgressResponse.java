package com.example.userprogressservice.presentation.web.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserQuizProgressResponse {
    private Long id;
    private Long userId;
    private Long quizId;
    private Double score;
    private Boolean completed;
    private Date dateTaken;//дата когда тест был пройден
    private Date createdAt;
    private Date updatedAt;
}
