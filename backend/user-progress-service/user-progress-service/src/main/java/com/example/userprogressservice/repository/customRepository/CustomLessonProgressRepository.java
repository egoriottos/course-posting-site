package com.example.userprogressservice.repository.customRepository;

import com.example.userprogressservice.domain.entity.UserLessonProgress;
import com.example.userprogressservice.searchParams.SearchLessonProgressParam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomLessonProgressRepository {

    private final EntityManager em;

    public List<UserLessonProgress> search(SearchLessonProgressParam params) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(UserLessonProgress.class);
        var root = query.from(UserLessonProgress.class);

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
        if (params.getCourseId() != null) {
            predicates.add(
                    cb.equal(root.get("courseId"),params.getCourseId()));
        }
        if (params.getLessonId() != null) {
            predicates.add(
                    cb.equal(root.get("lessonId"),params.getLessonId()));
        }
        if (params.getCompleted() != null) {
            predicates.add(
                    cb.equal(root.get("completed"),params.getCompleted()));
        }
        if (params.getCompletionDate() != null) {
            predicates.add(
                    cb.equal(root.get("completionDate"),params.getCompletionDate()));
        }
        if (params.getCreatedAt() != null) {
            predicates.add(
                    cb.equal(root.get("createdAt"),params.getCreatedAt()));
        }
        if (params.getUpdatedAt() != null) {
            predicates.add(
                    cb.equal(root.get("updatedAt"),params.getUpdatedAt()));
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
