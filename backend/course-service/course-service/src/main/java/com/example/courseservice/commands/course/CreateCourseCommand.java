package com.example.courseservice.commands.course;

import lombok.Data;

import java.util.Date;

@Data
public class CreateCourseCommand {
    private String title;

    private String description;

    private String author;

    private Date createdAt;
}
