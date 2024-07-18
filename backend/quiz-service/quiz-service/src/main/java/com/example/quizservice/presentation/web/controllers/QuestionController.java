package com.example.quizservice.presentation.web.controllers;

import com.example.quizservice.application.services.QuestionService;
import com.example.quizservice.commands.question.CreateQuestionCommand;
import com.example.quizservice.commands.question.SearchQuestionParams;
import com.example.quizservice.commands.question.UpdateQuestionCommand;
import com.example.quizservice.commands.question.response.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public String createQuestion(CreateQuestionCommand createQuestionCommand) {
        questionService.createQuestion(createQuestionCommand);
        return "Question created " + createQuestionCommand.getText()
                + " " +createQuestionCommand.getOptions()
                +" " +createQuestionCommand.getCorrectAnswers()
                + " " + createQuestionCommand.getImages()
                + " " + createQuestionCommand.getType()
                + " " + createQuestionCommand.getCreatedAt();
    }

    @PostMapping("/search")
    public List<QuestionResponse> search(SearchQuestionParams questionParams){
        return questionService.search(questionParams).stream()
                .map(obj -> modelMapper.map(obj, QuestionResponse.class)).collect(Collectors.toList());
    }
    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return "Question deleted " + id;
    }
    @GetMapping("/getAll")
    public List<QuestionResponse> getAllQuestions() {
        return questionService.getAllQuestions().stream()
                .map(obj -> modelMapper.map(obj, QuestionResponse.class)).collect(Collectors.toList());
    }
    @GetMapping("/get/{id}")
    public QuestionResponse getQuestion(@PathVariable Long id) {
        return modelMapper.map(questionService.getQuestionById(id), QuestionResponse.class);
    }
    @PutMapping("/update/{id}")
    public String updateQuestion(@PathVariable Long id, UpdateQuestionCommand updateQuestionCommand) {
       questionService.updateQuestion(id, updateQuestionCommand);
       return "Question updated " + updateQuestionCommand.getText()+" "+updateQuestionCommand.getOptions()+" "+updateQuestionCommand.getCorrectAnswers()+" "+updateQuestionCommand.getImages()+" "+updateQuestionCommand.getType();
    }
    @GetMapping("/correctAnswers/{id}")
    public List<QuestionResponse> getCorrectAnswers(@PathVariable Long id) {
        return questionService.getCorrectAnswers(id).stream()
                .map(obk -> modelMapper.map(obk, QuestionResponse.class)).collect(Collectors.toList());
    }
}
