package com.example.courseservice.application.commands.module;

import com.example.courseservice.domain.entities.Course;
import lombok.Data;

import java.util.List;
@Data
public class UpdateModuleCommand {

    private String title;

    private String description;

    private Integer order;

    private Course course;

    private List<Integer> lessonsId;

    private Integer quizId;
}
