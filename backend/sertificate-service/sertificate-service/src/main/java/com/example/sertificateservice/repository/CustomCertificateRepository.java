package com.example.sertificateservice.repository;

import com.example.sertificateservice.domain.entity.Certificate;
import com.example.sertificateservice.repository.params.SearchParams;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomCertificateRepository {
    private final EntityManager em;
    public List<Certificate> search(SearchParams params) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Certificate.class);

        var root = query.from(Certificate.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params.getId() != null) {
            predicates.add(
                    cb.equal(root.get("id"), params.getId())
            );
        }

        if (params.getCourseId() != null) {
            predicates.add(
                    cb.equal(root.get("courseId"), params.getCourseId())
            );
        }
        if (params.getCertificateUrl() != null) {
            predicates.add(
                    cb.equal(root.get("certificateUrl"), params.getCertificateUrl())
            );
        }
        if (params.getTemplateUrl() != null) {
            predicates.add(
                    cb.equal(root.get("templateUrl"), params.getTemplateUrl())
            );
        }
        if (params.getTemplateName() != null) {
            predicates.add(
                    cb.equal(root.get("templateName"), params.getTemplateName())
            );
        }
        if (params.getFirstName() != null) {
            predicates.add(
                    cb.equal(root.get("firstName"), params.getFirstName())
            );
        }
        if (params.getLastName() != null) {
            predicates.add(
                    cb.equal(root.get("lastName"), params.getLastName())
            );
        }
        if (params.getPatronymicName() != null) {
            predicates.add(
                    cb.equal(root.get("patronymicName"), params.getPatronymicName())
            );
        }
        if (params.getCourseName() != null) {
            predicates.add(
                    cb.equal(root.get("courseName"), params.getCourseName())
            );
        }
        if (params.getIssuedAt() != null) {
            predicates.add(
                    cb.equal(root.get("issuedAt"), params.getIssuedAt())
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
