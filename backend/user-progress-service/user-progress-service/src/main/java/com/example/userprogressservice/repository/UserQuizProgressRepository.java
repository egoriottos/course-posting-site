package com.example.userprogressservice.repository;

import com.example.userprogressservice.domain.entity.UserQuizProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuizProgressRepository extends JpaRepository<UserQuizProgress, Long> {
}
