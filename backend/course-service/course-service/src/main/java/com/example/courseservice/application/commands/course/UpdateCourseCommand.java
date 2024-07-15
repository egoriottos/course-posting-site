package com.example.courseservice.application.commands.course;

import com.example.courseservice.domain.entities.Module;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UpdateCourseCommand {
    private String title;

    private String description;

    private String author;

    private List<Module> modules;

    private Date updatedAt;
}
