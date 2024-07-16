package com.example.quizservice.repositories.customRepositories;

import com.example.quizservice.commands.question.SearchQuestionParams;
import com.example.quizservice.commands.quiz.SearchQuizParams;
import com.example.quizservice.domain.entity.Question;
import com.example.quizservice.domain.entity.Quiz;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomQuizRepository {

    private final EntityManager em;

    public List<Quiz> search(SearchQuizParams params) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Quiz.class);

        var root = query.from(Quiz.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params.getId() != null) {
            predicates.add(
                    cb.equal(root.get("id"), params.getId())
            );
        }
        if (params.getTitle() != null) {
            predicates.add(
                    cb.equal(root.get("title"), params.getTitle())
            );
        }
        if (params.getLessonId() != null) {
            predicates.add(
                    cb.equal(root.get("lesson").get("id"), params.getLessonId())
            );
        }
        if (params.getDescription() != null) {
            predicates.add(
                    cb.equal(root.get("description"), params.getDescription())
            );
        }
        if (params.getQuestions() != null) {
            predicates.add(
                    cb.equal(root.get("questions"), params.getQuestions())
            );
        }
        if (params.getCreatedAt() != null) {
            predicates.add(
                    cb.equal(root.get("createdAt"), params.getCreatedAt())
            );
        }
        if (params.getUpdatedAt() != null) {
            predicates.add(
                    cb.equal(root.get("updatedAt"), params.getUpdatedAt())
            );
        }

        final Predicate[] a = new Predicate[0];

        query.where(cb.and(predicates.toArray(a)));

        var typedQuery = em.createQuery(query);

        if (params.getLimit() != null && params.getLimit() > 0) {
            typedQuery.setMaxResults(params.getLimit());
        }

        if (params.getOffset() != null && params.getOffset() >= 0) {
            typedQuery.setFirstResult(params.getOffset());
        }

        return typedQuery.getResultList();

    }
}
