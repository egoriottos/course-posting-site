package com.example.quizservice.application.services;

import com.example.quizservice.commands.quiz.CreateQuizCommand;
import com.example.quizservice.commands.quiz.SearchQuizParams;
import com.example.quizservice.commands.quiz.UpdateQuizCommand;
import com.example.quizservice.commands.quiz.response.QuizDto;
import com.example.quizservice.domain.entity.Quiz;
import com.example.quizservice.repositories.QuizRepository;
import com.example.quizservice.repositories.customRepositories.CustomQuizRepository;
import jakarta.persistence.EntityNotFoundException;
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
public class QuizService {
    private final QuizRepository quizRepository;
    private final CustomQuizRepository quizCustomRepository;
    private final ModelMapper modelMapper;
    //создание теста
    public void createQuiz(CreateQuizCommand command){
        var quiz = Quiz.builder()
                .title(command.getTitle())
                .description(command.getDescription())
                .questions(command.getQuestions())
                .lessonId(command.getLessonId())                 //todo
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
        quizRepository.save(quiz);
    }
    //нахождение теста по его айди в таблице
    public QuizDto getQuizById(Long id){
        var quiz = quizRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(quiz, QuizDto.class);
    }
    //получение всех тестов
    public List<QuizDto> getAllQuizzes(){
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(obj->modelMapper.map(obj, QuizDto.class)).collect(Collectors.toList());
    }
    //обновление теста
    public void updateQuiz(Long id, UpdateQuizCommand command){
        var quiz = quizRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!quiz.getTitle().equals(command.getTitle())
                || !quiz.getDescription().equals(command.getDescription())
                || !quiz.getLessonId().equals(command.getLessonId())
                || !quiz.getQuestions().equals(command.getQuestions()))
        {
            quiz.setTitle(command.getTitle());
            quiz.setDescription(command.getDescription());
            quiz.setQuestions(command.getQuestions());
            quiz.setLessonId(command.getLessonId());
            quiz.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            quizRepository.save(quiz);
        }
    }
    //удаление теста
    public void deleteQuiz(Long id){
        quizRepository.deleteById(id);
    }
    //поиск списка тестов по одному/нескольким полям
    public List<QuizDto> search(SearchQuizParams params){
       return quizCustomRepository.search(params).stream()
                .map(obj->modelMapper.map(obj, QuizDto.class)).collect(Collectors.toList());
    }
}
