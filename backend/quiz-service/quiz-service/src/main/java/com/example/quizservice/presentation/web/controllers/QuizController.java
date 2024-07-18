package com.example.quizservice.presentation.web.controllers;

import com.example.quizservice.application.services.QuizService;
import com.example.quizservice.commands.quiz.CreateQuizCommand;
import com.example.quizservice.commands.quiz.SearchQuizParams;
import com.example.quizservice.commands.quiz.UpdateQuizCommand;
import com.example.quizservice.commands.quiz.response.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public String createQuiz(@RequestBody CreateQuizCommand command){
        quizService.createQuiz(command);
        return "Quiz created " + command.getTitle() + command.getDescription() + command.getQuestions() + command.getLessonId();
    }
    @GetMapping("/getById/{id}")
    public QuizResponse getQuizById(@PathVariable Long id){
        return modelMapper.map(quizService.getQuizById(id), QuizResponse.class);
    }
    @GetMapping("/all")
    public List<QuizResponse> getAllQuiz(){
       return quizService.getAllQuizzes().stream()
                .map(obj->modelMapper.map(obj, QuizResponse.class)).collect(Collectors.toList());
    }
    @PutMapping("/update/{id}")
    public String updateQuiz(@PathVariable Long id, @RequestBody UpdateQuizCommand command){
        quizService.updateQuiz(id, command);
        return "Quiz updated " + command.getTitle() + command.getDescription() + command.getQuestions();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteQuiz(@PathVariable Long id){
        quizService.deleteQuiz(id);
        return "Quiz deleted " + id;
    }
    @PostMapping("/search")
    public List<QuizResponse> search(@RequestBody SearchQuizParams params){
        return quizService.search(params).stream()
                .map(obj->modelMapper.map(obj, QuizResponse.class)).collect(Collectors.toList());
    }

}
