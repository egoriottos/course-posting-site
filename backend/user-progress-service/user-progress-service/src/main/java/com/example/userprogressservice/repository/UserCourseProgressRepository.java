package com.example.userprogressservice.repository;

import com.example.userprogressservice.domain.entity.UserCourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCourseProgressRepository extends JpaRepository<UserCourseProgress, Long> {
    List<UserCourseProgress> findByUserId(Long userId);

    UserCourseProgress findByUserIdAndCourseId(Long userId, Long courseId);
}
