package com.example.quizservice.commands.quiz.dto;

import com.example.quizservice.domain.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long id;
    private String title;
    private Long lessonId;
    private String description;
    private List<Question> questions;
    private Date createdAt;
    private Date updatedAt;
}
