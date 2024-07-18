package com.example.quizservice.commands.question.response;

import com.example.quizservice.domain.entity.Image;
import com.example.quizservice.domain.enums.QuestionType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionResponse {
    private Long id;
    private String text;
    private QuestionType type;
    private List<String> options; //вариантов ответа
    private List<String> correctAnswers; // правильные ответы
    private List<Image> images;// список изображений для вопроса
    private Date createdAt;
    private Date updatedAt;
}
