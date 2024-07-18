package com.example.quizservice.commands.quiz.response;

import com.example.quizservice.domain.entity.Question;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuizResponse {
    private Long id;
    private String title;
    private Long lessonId;
    private String description;
    private List<Question> questions;
    private Date createdAt;
    private Date updatedAt;
}
