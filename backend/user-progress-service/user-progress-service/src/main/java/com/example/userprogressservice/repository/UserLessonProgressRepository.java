package com.example.userprogressservice.repository;

import com.example.userprogressservice.domain.entity.UserLessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLessonProgressRepository extends JpaRepository<UserLessonProgress, Long> {
}
