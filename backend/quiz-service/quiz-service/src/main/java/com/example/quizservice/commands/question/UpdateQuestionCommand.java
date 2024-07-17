package com.example.quizservice.commands.question;

import com.example.quizservice.domain.entity.Image;
import com.example.quizservice.domain.enums.QuestionType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UpdateQuestionCommand {
    private String text;
    private QuestionType type;
    private List<String> options; //вариантов ответа
    private List<String> correctAnswers; // правильные ответы
    private List<Image> images;// список изображений для вопроса
    private Date updatedAt;
}
