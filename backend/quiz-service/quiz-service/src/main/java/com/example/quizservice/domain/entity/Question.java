package com.example.quizservice.domain.entity;

import com.example.quizservice.domain.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "question")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;
    @ElementCollection
    private List<String> options; //вариантов ответа
    @ElementCollection
    private List<String> correctAnswers; // правильные ответы
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;// список изображений для вопроса
    private Date createdAt;
    private Date updatedAt;

}
