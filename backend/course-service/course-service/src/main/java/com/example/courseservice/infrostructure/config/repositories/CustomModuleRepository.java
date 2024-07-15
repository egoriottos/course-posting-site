package com.example.courseservice.infrostructure.config.repositories;

import com.example.courseservice.application.commands.module.query.ModuleQuery;
import com.example.courseservice.domain.entities.Module;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomModuleRepository {
    private final EntityManager em;
    public List<Module> search(ModuleQuery params) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Module.class);

        var root = query.from(Module.class);

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
        if (params.getOrder() != null) {
            predicates.add(
                    cb.equal(root.get("order"),params.getOrder()));
        }
        if (params.getCourse() != null) {
            predicates.add(
                    cb.equal(root.get("course"),params.getCourse()));
        }
        if (params.getLessonsId() != null) {
            predicates.add(
                    cb.equal(root.get("lessons").get("id"),params.getLessonsId()));
        }
        if (params.getQuizId() != null) {
            predicates.add(
                    cb.equal(root.get("quiz").get("id"),params.getLessonsId()));
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
