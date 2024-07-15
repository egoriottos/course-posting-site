package com.example.courseservice.application.commands.module;

import com.example.courseservice.domain.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleResponse {
    private Long id;
    private String title;
    private String description;
    private Integer order;
    private Course course;
    private List<Integer> lessonsId;
    private Integer quizId;
    private Date createdAt;
    private Date updatedAt;
}
