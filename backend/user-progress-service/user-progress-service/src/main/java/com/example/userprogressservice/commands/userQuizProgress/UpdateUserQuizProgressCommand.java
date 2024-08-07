package com.example.userprogressservice.commands.userQuizProgress;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserQuizProgressCommand {
    private Long userId;
    private Long quizId;
    private Double score;
    private Boolean completed;
    private Date dateTaken;//дата когда тест был пройден
}
