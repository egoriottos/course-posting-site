package com.example.courseservice.presentation.web.controller;

import com.example.courseservice.application.commands.course.CourseResponse;
import com.example.courseservice.application.commands.course.CreateCourseCommand;
import com.example.courseservice.application.commands.course.UpdateCourseCommand;
import com.example.courseservice.application.commands.course.query.CourseQuery;
import com.example.courseservice.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @PostMapping("/search")
    public List<CourseResponse> search(@RequestBody CourseQuery query) {
        return courseService.search(query);
    }

    @PostMapping("/create")
    public String create(@RequestBody CreateCourseCommand command) {
        courseService.createCourse(command);
        return "Course created"+ command.getTitle() +" "
                + command.getDescription()
                +" "+ command.getAuthor()+" "+ command.getCreatedAt();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "Course deleted"+ id;
    }
    @PutMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id, @RequestBody UpdateCourseCommand command) {
        courseService.updateCourse(id, command);
        return "Course updated"+ id + " "+ command.getTitle()
                +" "+ command.getDescription()
                +" "+ command.getAuthor()
                +" "+ command.getUpdatedAt()
                +" "+ command.getModules();
    }
}
