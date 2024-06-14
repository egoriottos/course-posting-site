package com.example.userservice.repository;

import com.example.userservice.domain.entity.Teacher;
import com.example.userservice.query.SearchTeacherQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomTeacherRepository {
    private final EntityManager em;

    public List<Teacher> search(SearchTeacherQuery params) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Teacher.class);

        var root = query.from(Teacher.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params.getId() != null) {
            predicates.add(
                    cb.equal(root.get("id"), params.getId())
            );
        }
        if (params.getOwnerId() != null) {
            predicates.add(
                    cb.equal(root.get("owner").get("id"), params.getOwnerId())
            );
        }
        if (params.getCoursesId() != null) {
            predicates.add(
                    cb.equal(root.get("courses").get("id"),params.getCoursesId()));
        }
        if (params.getRating() != null) {
            predicates.add(
                    cb.equal(root.get("rating").get("id"),params.getRating()));
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
