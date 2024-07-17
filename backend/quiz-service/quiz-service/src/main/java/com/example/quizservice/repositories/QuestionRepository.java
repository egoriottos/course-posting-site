package com.example.quizservice.repositories;

import com.example.quizservice.domain.entity.Question;
import com.example.quizservice.domain.enums.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByType(QuestionType type);
}
