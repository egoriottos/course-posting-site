package com.example.userprogressservice.presentation.web.controllers;

import com.example.userprogressservice.application.services.UserLessonProgressService;
import com.example.userprogressservice.commands.userLessonProgress.CreateUserLessonProgressCommand;
import com.example.userprogressservice.commands.userLessonProgress.UpdateUserLessonProgressCommand;
import com.example.userprogressservice.presentation.web.response.UserLessonProgressResponse;
import com.example.userprogressservice.searchParams.SearchLessonProgressParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/lesson/progress")
@RequiredArgsConstructor
public class UserLessonProgressController {
    private final UserLessonProgressService userLessonProgressService;
    private final ModelMapper modelMapper;

    @PostMapping("/createProgress")
    public String create(@RequestBody CreateUserLessonProgressCommand command) {
        userLessonProgressService.startLessonProgress(command);
        return "Lesson progress created";
    }

    @PostMapping("/completeLesson/{userId}/{lessonId}")
    public String completeLessonProgress(@PathVariable Long userId, @PathVariable Long lessonId) {
        userLessonProgressService.completeLessonProgress(userId, lessonId);
        return "Lesson progress completed";
    }

    @DeleteMapping("/delete/{userId}/{lessonId}")
    public String deleteLessonProgress(@PathVariable Long userId, @PathVariable Long lessonId) {
        userLessonProgressService.deleteLessonProgress(userId, lessonId);
        return "Lesson progress deleted " + userId + " " + lessonId;
    }

    @GetMapping("/completedLessons/{userId}")
    public List<UserLessonProgressResponse> completedLessons(@PathVariable Long userId, @RequestParam Boolean completed) {
        return userLessonProgressService.getCompletedLessons(userId, completed).stream()
                .map(obj -> modelMapper.map(obj, UserLessonProgressResponse.class)).collect(Collectors.toList());
    }

    @PutMapping("/update/{id}")
    public String updateLessonProgress(@PathVariable Long id, @RequestBody UpdateUserLessonProgressCommand command) {
        userLessonProgressService.updateUserLessonProgress(id, command);
        return "Lesson progress updated " + id + " " + command.getLessonId();
    }

    @GetMapping("/lessonProgress/{id}")
    public UserLessonProgressResponse getLessonProgress(@PathVariable Long id) {
        return modelMapper.map(userLessonProgressService.getUserLessonProgress(id), UserLessonProgressResponse.class);
    }

    @PostMapping("/search")
    public List<UserLessonProgressResponse> search(@RequestBody SearchLessonProgressParam param) {
        return userLessonProgressService.search(param).stream().map(obj -> modelMapper.map(obj, UserLessonProgressResponse.class)).collect(Collectors.toList());
    }
}