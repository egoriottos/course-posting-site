package com.example.quizservice.commands.quiz;

import com.example.quizservice.domain.entity.Question;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateQuizCommand {
    private String title;
    private Long lessonId;
    private String description;
    private List<Question> questions;
}
