package com.example.lessonservice.repository.CustomRepository;

import com.example.lessonservice.commands.Attachment.AttachmentSearch;
import com.example.lessonservice.entities.Attachment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomAttachmentRepository {
    private final EntityManager em;

    public List<Attachment> search(AttachmentSearch searchParams) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Attachment.class);

        var root = query.from(Attachment.class);

        List<Predicate> predicates = new ArrayList<>();


        if (searchParams.getId() != null) {
            predicates.add(cb.equal(root.get("id"), searchParams.getId()));
        }

        if (searchParams.getLesson() != null) {
            predicates.add(cb.equal(root.get("lesson"), searchParams.getLesson()));
        }
        if (searchParams.getFileName() != null) {
            predicates.add(cb.equal(root.get("fileName"), searchParams.getFileName()));
        }

        if (searchParams.getUrl() != null) {
            predicates.add(cb.equal(root.get("url"), searchParams.getUrl()));
        }

        if (searchParams.getCreatedAt() != null) {
            predicates.add(cb.equal(root.get("createdAt"), searchParams.getCreatedAt()));
        }
        if (searchParams.getUpdatedAt()!= null) {
            predicates.add(cb.equal(root.get("updatedAt"), searchParams.getUpdatedAt()));
        }

        final Predicate[] a = new Predicate[0];

        query.where(cb.and(predicates.toArray(a)));

        var typedQuery = em.createQuery(query);

        if (searchParams.getLimit() != null && searchParams.getLimit() > 0) {
            typedQuery.setMaxResults(searchParams.getLimit());
        }

        if (searchParams.getOffset() != null && searchParams.getOffset() >= 0) {
            typedQuery.setFirstResult(searchParams.getOffset());
        }

        return typedQuery.getResultList();
    }
}
