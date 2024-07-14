package com.example.courseservice.repositories;

import com.example.courseservice.commands.course.query.CourseQuery;
import com.example.courseservice.entities.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomCourseRepository {
    private final EntityManager em;
//    private Long id;+
//    private String title;+
//    private String description;+
//    private String author;+
//    private List<Module> modules;+
//    private Date createdAt;
//    private Date updatedAt;

    public List<Course> search(CourseQuery params) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Course.class);

        var root = query.from(Course.class);

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
        if (params.getDescription() != null) {
            predicates.add(
                    cb.equal(root.get("description"),params.getDescription()));
        }
        if (params.getAuthor() != null) {
            predicates.add(
                    cb.equal(root.get("author"),params.getAuthor()));
        }
        if (params.getModules() != null) {
            predicates.add(
                    cb.equal(root.get("modules"),params.getModules()));
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
