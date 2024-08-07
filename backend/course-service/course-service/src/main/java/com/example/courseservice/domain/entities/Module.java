package com.example.courseservice.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "modules")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    private Integer serialNumber;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ElementCollection
    @Column(name = "lessons_id")
    private List<Integer> lessonsId;
    @Column(name = "quiz_id")
    private Integer quizId;
    private Date createdAt;
    private Date updatedAt;
}
