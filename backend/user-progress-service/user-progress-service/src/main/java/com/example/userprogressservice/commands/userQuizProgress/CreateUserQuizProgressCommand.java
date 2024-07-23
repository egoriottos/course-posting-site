package com.example.userprogressservice.commands.userQuizProgress;

import lombok.Data;

@Data
public class CreateUserQuizProgressCommand {
    private Long userId;
    private Long quizId;
}
