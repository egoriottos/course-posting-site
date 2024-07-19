package com.example.userprogressservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user_lesson_progress")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLessonProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long courseId;
    private Long lessonId;
    private Boolean completed;
    @Column
    private Date completionDate;
    private Date createdAt;
    private Date updatedAt;
}
