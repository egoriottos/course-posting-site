package com.example.userprogressservice.application.services;

import com.example.userprogressservice.commands.userLessonProgress.CreateUserLessonProgressCommand;
import com.example.userprogressservice.commands.userLessonProgress.UpdateUserLessonProgressCommand;
import com.example.userprogressservice.domain.entity.UserLessonProgress;
import com.example.userprogressservice.dto.UserLessonProgressDto;
import com.example.userprogressservice.repository.UserLessonProgressRepository;
import com.example.userprogressservice.repository.customRepository.CustomLessonProgressRepository;
import com.example.userprogressservice.searchParams.SearchLessonProgressParam;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserLessonProgressService {
    private final UserLessonProgressRepository userLessonProgressRepository;
    private final CustomLessonProgressRepository lessonProgressRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void startLessonProgress(CreateUserLessonProgressCommand command) {
        var lessonProgress = UserLessonProgress.builder()
                .userId(command.getUserId())
                .courseId(command.getCourseId())
                .lessonId(command.getLessonId())
                .completed(false)
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
        userLessonProgressRepository.save(lessonProgress);
    }
    @Transactional
    public void completeLessonProgress(Long userId, Long lessonId){
        var userLessonProgress = userLessonProgressRepository.findByUserIdAndLessonId(userId, lessonId);
        userLessonProgress.setCompleted(true);
        userLessonProgress.setCompletionDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        userLessonProgressRepository.save(userLessonProgress);
    }
    @Transactional
    public void deleteLessonProgress(Long userId, Long lessonId){
        userLessonProgressRepository.deleteByUserIdAndLessonId(userId, lessonId);
    }
    public List<UserLessonProgressDto> getCompletedLessons(Long userId, Boolean completed){
        return userLessonProgressRepository.findByUserIdAndCompleted(userId, completed).stream()
                .map(obj->modelMapper.map(obj, UserLessonProgressDto.class)).collect(Collectors.toList());
    }
    @Transactional
    public void updateUserLessonProgress(Long id, UpdateUserLessonProgressCommand command){
        var lessonProgress =userLessonProgressRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User Progress Not Found"));
        if(!lessonProgress.getLessonId().equals(command.getLessonId())
                || !lessonProgress.getUserId().equals(command.getUserId()))
        {
            lessonProgress.setLessonId(command.getLessonId());
            lessonProgress.setUserId(command.getUserId());
            lessonProgress.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            userLessonProgressRepository.save(lessonProgress);
        }
    }
    public UserLessonProgressDto getUserLessonProgress(Long id){
       var userProgress = userLessonProgressRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User Progress Not Found"));
       return modelMapper.map(userProgress, UserLessonProgressDto.class);
    }
    public List<UserLessonProgressDto> search(SearchLessonProgressParam param){
      return lessonProgressRepository.search(param).stream()
              .map(obj->modelMapper.map(obj, UserLessonProgressDto.class)).collect(Collectors.toList());
    }

}
