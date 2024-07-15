package com.example.courseservice.application.commands.course;

import com.example.courseservice.domain.entities.Module;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private String author;
    private List<Module> modules;
    private Date createdAt;
    private Date updatedAt;
}
