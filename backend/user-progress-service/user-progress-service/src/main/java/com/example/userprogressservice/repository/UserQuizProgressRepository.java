package com.example.userprogressservice.repository;

import com.example.userprogressservice.domain.entity.UserQuizProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQuizProgressRepository extends JpaRepository<UserQuizProgress, Long> {
    Optional<UserQuizProgress> findByUserIdAndQuizId(Long userId, Long quizId);

    void deleteByUserIdAndQuizId(Long userId, Long quizId);
}
