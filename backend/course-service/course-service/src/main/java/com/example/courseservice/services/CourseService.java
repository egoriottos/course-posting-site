package com.example.courseservice.services;

import com.example.courseservice.commands.course.CreateCourseCommand;
import com.example.courseservice.commands.course.UpdateCourseCommand;
import com.example.courseservice.entities.Course;
import com.example.courseservice.repositories.CourseRepository;
import com.example.courseservice.repositories.CustomCourseRepository;
import com.example.courseservice.repositories.ModuleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final CustomCourseRepository courseCustomRepository;
    @Transactional
    public void createCourse(CreateCourseCommand course) {
        var courseEntity = Course.builder()
                .title(course.getTitle())
                .description(course.getDescription())
                .author(course.getAuthor())
                .createdAt((Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())))
                .build();
        courseRepository.save(courseEntity);
    }
    @Transactional
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public void deleteCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            throw new IllegalArgumentException("Course not found");
        }

        Course course = courseOptional.get();
        // Удаление связанных модулей
        moduleRepository.deleteModulesByCourse(course);
        // Удаление курса
        courseRepository.delete(course);
    }
    @Transactional
    public Course updateCourse(Long id, UpdateCourseCommand course) {
        Course courseEntity = getCourseById(id);
        if(!courseEntity.getTitle().equals(course.getTitle())
                || !courseEntity.getDescription().equals(course.getDescription())
                || !courseEntity.getAuthor().equals(course.getAuthor())
                || !courseEntity.getModules().equals(course.getModules()))
        {
            courseEntity.setTitle(course.getTitle());
            courseEntity.setDescription(course.getDescription());
            courseEntity.setAuthor(course.getAuthor());
            courseEntity.setModules(course.getModules());
            courseEntity.setUpdatedAt((Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));
        }
        return courseRepository.save(courseEntity);
    }
}
