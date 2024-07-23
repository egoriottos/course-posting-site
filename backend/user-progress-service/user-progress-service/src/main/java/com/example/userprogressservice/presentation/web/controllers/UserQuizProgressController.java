package com.example.userprogressservice.presentation.web.controllers;

import com.example.userprogressservice.application.services.UserQuizProgressService;
import com.example.userprogressservice.commands.userQuizProgress.CreateUserQuizProgressCommand;
import com.example.userprogressservice.commands.userQuizProgress.UpdateUserQuizProgressCommand;
import com.example.userprogressservice.presentation.web.response.UserQuizProgressResponse;
import com.example.userprogressservice.searchParams.SearchQuizProgressParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/quiz/progress")
@RequiredArgsConstructor
public class UserQuizProgressController {
    private final UserQuizProgressService userQuizProgressService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public UserQuizProgressResponse create(@RequestBody CreateUserQuizProgressCommand command) {
        return modelMapper.map(userQuizProgressService.startQiz(command), UserQuizProgressResponse.class);
    }

    @PutMapping("/finish/{userId}/{quizId}")
    public String finish(@PathVariable Long userId, @PathVariable Long quizId) {
        userQuizProgressService.finishQiz(userId, quizId);
        return "success";
    }

    @PutMapping("/update/{userId}/{quizId}")
    public String update(@PathVariable Long userId, @PathVariable Long quizId, @RequestBody UpdateUserQuizProgressCommand command) {
        userQuizProgressService.updateQuizProgress(userId, quizId, command);
        return "success " + userId + " " + quizId;
    }

    @PutMapping("/update/onQuestions/{userId}/{quizId}")
    public UserQuizProgressResponse updateQuizProgressBasedOnQuestions(@PathVariable Long userId, @PathVariable Long quizId, @RequestParam int totalQuestions) {
        return modelMapper.map(userQuizProgressService.updateQuizProgressBasedOnQuestions(userId, quizId, totalQuestions), UserQuizProgressResponse.class);
    }

    @DeleteMapping("/delete/{userId}/{quizId}")
    public String delete(@PathVariable Long userId, @PathVariable Long quizId) {
        userQuizProgressService.deleteQuizProgress(userId, quizId);
        return "delete success " + userId + " " + quizId;
    }

    @PostMapping("/search")
    public List<UserQuizProgressResponse> search(@RequestBody SearchQuizProgressParam param) {
        return userQuizProgressService.getUserQuizProgress(param).stream()
                .map(obj -> modelMapper.map(obj, UserQuizProgressResponse.class)).collect(Collectors.toList());
    }

    @GetMapping("/getAll")
    public List<UserQuizProgressResponse> getAll() {
        return userQuizProgressService.getAll().stream()
                .map(obj -> modelMapper.map(obj, UserQuizProgressResponse.class)).collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public UserQuizProgressResponse getById(@PathVariable Long id) {
        return modelMapper.map(userQuizProgressService.getUserQuizProgress(id), UserQuizProgressResponse.class);
    }
}
