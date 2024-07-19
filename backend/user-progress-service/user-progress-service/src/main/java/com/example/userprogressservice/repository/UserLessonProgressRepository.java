package com.example.userprogressservice.repository;

import com.example.userprogressservice.domain.entity.UserLessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLessonProgressRepository extends JpaRepository<UserLessonProgress, Long> {
    UserLessonProgress findByUserIdAndLessonId(Long userId, Long lessonId);
    void deleteByUserIdAndLessonId(Long userId, Long lessonId);
    List<UserLessonProgress> findByUserIdAndCompleted(Long userId, Boolean completed);
}
