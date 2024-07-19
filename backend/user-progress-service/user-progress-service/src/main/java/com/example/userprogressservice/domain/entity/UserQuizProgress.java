package com.example.userprogressservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user_quiz_progress")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuizProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long quizId;
    private Double score;
    private Boolean completed;
    private Date dateTaken;//дата когда тест был пройден
    private Date createdAt;
    private Date updatedAt;
}
