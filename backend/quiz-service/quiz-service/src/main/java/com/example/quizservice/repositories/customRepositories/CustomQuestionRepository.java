package com.example.quizservice.repositories.customRepositories;

import com.example.quizservice.commands.question.SearchQuestionParams;
import com.example.quizservice.domain.entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomQuestionRepository {
    private final EntityManager em;
    public List<Question> search(SearchQuestionParams params) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Question.class);

        var root = query.from(Question.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params.getId() != null) {
            predicates.add(
                    cb.equal(root.get("id"), params.getId())
            );
        }
        if (params.getText() != null) {
            predicates.add(
                    cb.equal(root.get("text"), params.getText())
            );
        }
        if (params.getType() != null) {
            predicates.add(
                    cb.equal(root.get("type"), params.getType())
            );
        }
        if (params.getOptions() != null) {
            predicates.add(
                    cb.equal(root.get("options"), params.getOptions())
            );
        }
        if (params.getCorrectAnswers() != null) {
            predicates.add(
                    cb.equal(root.get("correctAnswers"), params.getCorrectAnswers())
            );
        }
        if (params.getImages() != null) {
            predicates.add(
                    cb.equal(root.get("images"), params.getImages())
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
