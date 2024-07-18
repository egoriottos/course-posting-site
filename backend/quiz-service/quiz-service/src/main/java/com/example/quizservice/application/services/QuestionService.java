package com.example.quizservice.application.services;

import com.example.quizservice.commands.question.CreateQuestionCommand;
import com.example.quizservice.commands.question.SearchQuestionParams;
import com.example.quizservice.commands.question.UpdateQuestionCommand;
import com.example.quizservice.commands.question.dto.QuestionDto;
import com.example.quizservice.domain.entity.Question;
import com.example.quizservice.domain.enums.QuestionType;
import com.example.quizservice.repositories.QuestionRepository;
import com.example.quizservice.repositories.customRepositories.CustomQuestionRepository;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final CustomQuestionRepository questionCustomRepository;
    private final ModelMapper modelMapper;
    //созадть вопрос
    //обратите внимание что сначала создается картинка а потом уже вставляется в вопрос
    // она не загружается одновременно с вопросом
    public void createQuestion(CreateQuestionCommand command){
        var question = Question.builder()
                .text(command.getText())
                .type(command.getType())
                .options(command.getOptions())
                .correctAnswers(command.getCorrectAnswers())
                .images(command.getImages())
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
        questionRepository.save(question);
    }
    //получить все вопросы
    public List<QuestionDto> getAllQuestions(){
        return questionRepository.findAll().stream()
                .map(obj -> modelMapper.map(obj, QuestionDto.class)).collect(Collectors.toList());
    }
    // поиск списка вопросов по 1 или нескольким полям, но нужно доп методы поиска из репозитория для применения в методах
    public List<QuestionDto> search(SearchQuestionParams params){
       return questionCustomRepository.search(params).stream()
                .map(obj -> modelMapper.map(obj, QuestionDto.class)).collect(Collectors.toList());
    }
    // получить вопрос по айди
    public QuestionDto getQuestionById(Long id){
        return modelMapper.map(questionRepository.findById(id), QuestionDto.class);
    }
//    обновить все параметры вопроса кроме картинки.
//    Картинка будет обновляться в своей базе, а вопрос уже будет вызывать обновленную картинку,
//    пока что не придумал как улучшить этот метод
    public void updateQuestion(Long id, UpdateQuestionCommand command){
        var question = questionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!question.getText().equals(command.getText())
                || !question.getType().equals(command.getType())
                || !question.getOptions().equals(command.getOptions())
                || !question.getCorrectAnswers().equals(command.getCorrectAnswers()))
        {
            question.setText(command.getText());
            question.setType(command.getType());
            question.setOptions(command.getOptions());
            question.setCorrectAnswers(command.getCorrectAnswers());
            question.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            questionRepository.save(question);
        }
    }
    // удалить вопрос
    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }
    // список вопросов по типу(несколько вариантов ответа, один ответ, ввод текста)
    public List<QuestionDto> getQuestionsByType(QuestionType type){
        return questionRepository.findByType(type)
                .stream().map(obj -> modelMapper.map(obj, QuestionDto.class)).collect(Collectors.toList());
    }
    //запрос правильных ответов по вопросу
    public List<QuestionDto> getCorrectAnswers(Long id){
       var question = questionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
       return question.getCorrectAnswers().stream()
               .map(obj -> modelMapper.map(obj, QuestionDto.class)).collect(Collectors.toList());

    }
}
