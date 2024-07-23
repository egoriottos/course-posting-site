package com.example.userprogressservice.presentation.web.controllers;

import com.example.userprogressservice.application.services.UserCourseProgressService;
import com.example.userprogressservice.commands.userCourseProgress.CreateUserCourseProgressCommand;
import com.example.userprogressservice.commands.userCourseProgress.UpdateUserCourseProgressCommand;
import com.example.userprogressservice.presentation.web.response.UserCourseProgressResponse;
import com.example.userprogressservice.searchParams.SearchCourseProgressParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/course/progress")
@RequiredArgsConstructor
public class UserCourseProgressController {
    private final UserCourseProgressService userCourseProgressService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public String create(@RequestBody CreateUserCourseProgressCommand command) {
        userCourseProgressService.createUserCourseProgress(command);
        return "User was created "
                + command.getUserId()
                + " " + command.getCourseId()
                + " " + command.getCompleted()
                + " " + command.getProgressPercentage();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userCourseProgressService.deleteCourseProgress(id);
        return "Course progress was deleted successfully " + id;
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody UpdateUserCourseProgressCommand command) {
        userCourseProgressService.updateUserCourseProgress(id, command);
        return "Course progress was updated successfully "
                + id + " " + command.getUserId()
                + " " + command.getCourseId()
                + " " + command.getProgressPercentage();
    }

    @GetMapping("/get/byUser/{userId}")
    public List<UserCourseProgressResponse> getByUserId(@PathVariable Long userId) {
        return userCourseProgressService.getUserCoursesProgress(userId).stream()
                .map(obj -> modelMapper.map(obj, UserCourseProgressResponse.class)).collect(Collectors.toList());
    }

    @GetMapping("/get/byUser/course/{userId}/{courseId}")
    public UserCourseProgressResponse getCourseProgressByUserIdAndCourseId(@PathVariable Long userId, @PathVariable Long courseId) {
        return modelMapper.map(userCourseProgressService.getUserCourseProgress(userId, courseId), UserCourseProgressResponse.class);

    }

    @GetMapping("/get/{id}")
    public UserCourseProgressResponse getUserCourseProgress(@PathVariable Long id) {
        return modelMapper.map(userCourseProgressService.getUserCourseProgress(id), UserCourseProgressResponse.class);
    }

    @PutMapping("/incrementProgress/{userId}/{courseId}/{lessonId}/{testId}")
    public UserCourseProgressResponse increment(@PathVariable Long userId, @PathVariable Long courseId, @PathVariable Long lessonId, @PathVariable Long testId) {
        return modelMapper.map(userCourseProgressService.incrementProgress(userId, courseId, lessonId, testId), UserCourseProgressResponse.class);
    }

    @PostMapping("/search")
    public List<UserCourseProgressResponse> search(@RequestBody SearchCourseProgressParam param) {
        return userCourseProgressService.search(param).stream()
                .map(obj -> modelMapper.map(obj, UserCourseProgressResponse.class)).collect(Collectors.toList());
    }
}
