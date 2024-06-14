package com.example.courseservice.commands.module;

import com.example.courseservice.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModuleCommand {

    private String title;

    private String description;

    private Integer order;

    private Course course;

    private Integer quizId;

    private List<Integer> lessonsId;

}
