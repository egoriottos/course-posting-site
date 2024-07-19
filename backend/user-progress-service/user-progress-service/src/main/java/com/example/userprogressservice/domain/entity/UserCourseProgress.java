package com.example.userprogressservice.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_course_progress")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long courseId;
    @Column(nullable = false)
    @Min(0)
    @Max(100)
    private Double progressPercentage;

    private Boolean completed;

    private Date createdAt;
    private Date updatedAt;

    @ElementCollection
    @CollectionTable(name = "completed_lessons", joinColumns = @JoinColumn(name = "user_course_progress_id"))
    @Column(name = "lesson_id")
    private List<Long> completedLessons = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "completed_tests", joinColumns = @JoinColumn(name = "user_course_progress_id"))
    @Column(name = "test_id")
    private List<Long> completedTests = new ArrayList<>();
}
