package com.example.userprogressservice.repository.customRepository;

import com.example.userprogressservice.domain.entity.UserQuizProgress;
import com.example.userprogressservice.searchParams.SearchQuizProgressParam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomQuizProgressRepository {
    private final EntityManager em;

    public List<UserQuizProgress> search(SearchQuizProgressParam params) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(UserQuizProgress.class);
        var root = query.from(UserQuizProgress.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params.getId() != null) {
            predicates.add(
                    cb.equal(root.get("id"), params.getId())
            );
        }
        if (params.getUserId() != null) {
            predicates.add(
                    cb.equal(root.get("userId"), params.getUserId())
            );
        }
        if (params.getQuizId() != null) {
            predicates.add(
                    cb.equal(root.get("quizId"), params.getQuizId()));
        }
        if (params.getScore() != null) {
            predicates.add(
                    cb.equal(root.get("score"), params.getScore()));
        }
        if (params.getCompleted() != null) {
            predicates.add(
                    cb.equal(root.get("completed"), params.getCompleted()));
        }
        if (params.getDateTaken() != null) {
            predicates.add(
                    cb.equal(root.get("dateTaken"), params.getDateTaken()));
        }
        if (params.getCreatedAt() != null) {
            predicates.add(
                    cb.equal(root.get("createdAt"), params.getCreatedAt()));
        }
        if (params.getUpdatedAt() != null) {
            predicates.add(
                    cb.equal(root.get("updatedAt"), params.getUpdatedAt()));
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
