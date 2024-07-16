package com.example.lessonservice.commands.Lesson.lessonController;

import com.example.lessonservice.commands.Lesson.CreateLessonCommand;
import com.example.lessonservice.commands.Lesson.LessonSearch;
import com.example.lessonservice.commands.Lesson.UpdateLessonCommand;
import com.example.lessonservice.commands.Lesson.response.LessonResponse;
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
    public LessonResponse createLesson(@RequestBody CreateLessonCommand lesson) {
        return lessonService.createLesson(lesson);
    }

    @PutMapping("/update/{id}")
    public LessonResponse updateLesson(@PathVariable Long id, @RequestBody UpdateLessonCommand lesson) {
       return lessonService.updateLesson(id,lesson);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLesson(@PathVariable Long id){
        lessonService.deleteLesson(id);
        return "Lesson was successfully deleted";
    }
    @PostMapping("/search")
    public List<LessonResponse> searchLessons(@RequestBody LessonSearch search) {
        return lessonService.search(search);
    }
    @GetMapping("/getBy/{id}")
    public LessonResponse getLesson(@PathVariable Long id) {
        return lessonService.getLesson(id);
    }
    @GetMapping("lessons/byCourse{id}")
    public List<LessonResponse> getLessonsByCourse(@PathVariable Long id) {
        return lessonService.getLessonsByCourseId(id);
    }



}
