package com.example.userprogressservice.application.services;

import com.example.userprogressservice.commands.userQuizProgress.CreateUserQuizProgressCommand;
import com.example.userprogressservice.commands.userQuizProgress.UpdateUserQuizProgressCommand;
import com.example.userprogressservice.domain.entity.UserQuizProgress;
import com.example.userprogressservice.dto.UserQuizProgressDto;
import com.example.userprogressservice.repository.UserQuizProgressRepository;
import com.example.userprogressservice.repository.customRepository.CustomQuizProgressRepository;
import com.example.userprogressservice.searchParams.SearchQuizProgressParam;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserQuizProgressService {
    private final UserQuizProgressRepository userQuizProgressRepository;
    private final RestTemplate restTemplate;
    private final CustomQuizProgressRepository customQuizProgressRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public UserQuizProgressDto startQiz(CreateUserQuizProgressCommand command) {
        var newQuizProgress = UserQuizProgress.builder()
                .userId(command.getUserId())
                .quizId(command.getQuizId())
                .score(0.0)
                .completed(false)
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
        return modelMapper.map(userQuizProgressRepository.save(newQuizProgress), UserQuizProgressDto.class);
    }

    @Transactional
    public void finishQiz(Long userId, Long quizId) {
        var quizProgress = userQuizProgressRepository.findByUserIdAndQuizId(userId, quizId).orElseThrow(EntityExistsException::new);
        quizProgress.setScore(100.0);
        quizProgress.setCompleted(true);
        quizProgress.setDateTaken(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        userQuizProgressRepository.save(quizProgress);
    }

    @Transactional
    public void updateQuizProgress(Long userId, Long quizId, UpdateUserQuizProgressCommand command) {
        var quizProgress = userQuizProgressRepository.findByUserIdAndQuizId(userId, quizId);
        if (quizProgress.isPresent()) {
            if (!quizProgress.get().getUserId().equals(command.getUserId())
                    || !quizProgress.get().getQuizId().equals(command.getQuizId())
                    || !quizProgress.get().getScore().equals(command.getScore())
                    || !quizProgress.get().getDateTaken().equals(command.getDateTaken())
                    || !quizProgress.get().getCompleted().equals(command.getCompleted())) {
                quizProgress.get().setScore(command.getScore());
                quizProgress.get().setDateTaken(command.getDateTaken());
                quizProgress.get().setCompleted(command.getCompleted());
                quizProgress.get().setUserId(command.getUserId());
                quizProgress.get().setQuizId(command.getQuizId());
                userQuizProgressRepository.save(quizProgress.get());
            }
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public UserQuizProgress updateQuizProgressBasedOnQuestions(Long userId, Long quizId, int totalQuestions) {
        var quizProgress = userQuizProgressRepository.findByUserIdAndQuizId(userId, quizId)
                .orElseThrow(() -> new EntityNotFoundException("Progress not found"));

        // Получить количество правильных ответов из микросервиса тестов
        int correctAnswers = getCorrectAnswersCount(quizId, userId);

        double correctPercentage = (correctAnswers / (double) totalQuestions) * 100.0;
        boolean isPassed = correctPercentage >= 75.0;

        quizProgress.setScore(100.0); // Предполагается, что тест завершен после выполнения всех вопросов
        quizProgress.setCompleted(isPassed);
        quizProgress.setScore(correctPercentage);

        return userQuizProgressRepository.save(quizProgress);
    }

    private int getCorrectAnswersCount(Long quizId, Long userId) {
        String url = String.format("%s/%d/users/%d/correct-answers-count", "http://quiz-service/api/quizzes", quizId, userId);
        return restTemplate.getForObject(url, Integer.class);
    }

    @Transactional
    public void deleteQuizProgress(Long userId, Long quizId) {
        userQuizProgressRepository.deleteByUserIdAndQuizId(userId, quizId);
    }

    public List<UserQuizProgressDto> getUserQuizProgress(SearchQuizProgressParam params) {
        return customQuizProgressRepository.search(params).stream()
                .map(obj -> modelMapper.map(obj, UserQuizProgressDto.class)).collect(Collectors.toList());
    }

    public UserQuizProgressDto getUserQuizProgress(Long id) {
        return modelMapper.map(userQuizProgressRepository.findById(id), UserQuizProgressDto.class);
    }

    public List<UserQuizProgressDto> getAll() {
        return userQuizProgressRepository.findAll().stream()
                .map(obj -> modelMapper.map(obj, UserQuizProgressDto.class)).collect(Collectors.toList());
    }

}
