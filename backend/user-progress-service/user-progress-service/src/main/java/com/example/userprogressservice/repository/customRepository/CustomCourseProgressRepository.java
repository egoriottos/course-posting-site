package com.example.userprogressservice.repository.customRepository;

import com.example.userprogressservice.domain.entity.UserCourseProgress;
import com.example.userprogressservice.searchParams.SearchCourseProgressParam;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomCourseProgressRepository {
    private final EntityManager em;

    public List<UserCourseProgress> search(SearchCourseProgressParam params) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(UserCourseProgress.class);
        var root = query.from(UserCourseProgress.class);

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
                    cb.equal(root.get("courseId"), params.getCourseId()));
        }
        if (params.getProgressPercentage() != null) {
            predicates.add(
                    cb.equal(root.get("progressPercentage"), params.getProgressPercentage()));
        }
        if (params.getCompleted() != null) {
            predicates.add(
                    cb.equal(root.get("completed"), params.getCompleted()));
        }
        if (params.getCreatedAt() != null) {
            predicates.add(
                    cb.equal(root.get("createdAt"), params.getCreatedAt()));
        }
        if (params.getUpdatedAt() != null) {
            predicates.add(
                    cb.equal(root.get("updatedAt"), params.getUpdatedAt()));
        }
        if (params.getCompletedLessons() != null) {
            predicates.add(
                    cb.equal(root.get("completedLessons"), params.getCompletedLessons()));
        }
        if (params.getCompletedTests() != null) {
            predicates.add(
                    cb.equal(root.get("completedTests"), params.getCompletedTests()));
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
