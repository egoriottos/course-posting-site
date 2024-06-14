package com.example.lessonservice.commands.Lesson.lessonController;

import com.example.lessonservice.commands.Lesson.lessonQuery.LessonQuery;
import com.example.lessonservice.entities.Lesson;
import com.example.lessonservice.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public LessonQuery createLesson(@RequestBody LessonQuery lesson) {
        Lesson createLesson = lessonService.createLesson(modelMapper.map(lesson, Lesson.class));
        return modelMapper.map(createLesson, LessonQuery.class);
    }

    @GetMapping("/get/all")
    public List<LessonQuery> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        return lessons.stream().map(lesson -> modelMapper.map(lesson, LessonQuery.class)).toList();
    }

    @GetMapping("/get/{id}")
    public LessonQuery getLessonById(@PathVariable Long id){
        Lesson lesson = lessonService.getLesson(id);
        return modelMapper.map(lesson, LessonQuery.class);
    }

    @PutMapping("/update/{id}")
    public LessonQuery updateLesson(@PathVariable Long id, @RequestBody LessonQuery lessonQuery) {
        Lesson lesson = lessonService.updateLesson(id, modelMapper.map(lessonQuery, Lesson.class));
        return modelMapper.map(lesson, LessonQuery.class);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLesson(@PathVariable Long id){
        lessonService.deleteLesson(id);
        return "Lesson was successfully deleted";
    }

    @GetMapping("/get/byCourse/{id}")
    public List<LessonQuery> getLessonsByCourse(@PathVariable Long id){
        List<Lesson> lessonList = lessonService.getLessonsByCourseId(id);
        return lessonList.stream().map(lesson -> modelMapper.map(lesson, LessonQuery.class)).toList();
    }

    @GetMapping("/get/byTitle")
    public List<LessonQuery> getLessonsByTitle(@RequestParam String title){
        List<Lesson> lessonList = lessonService.getLessonByTitle(title);
        return lessonList.stream().map(lesson -> modelMapper.map(lesson, LessonQuery.class)).toList();
    }

}
