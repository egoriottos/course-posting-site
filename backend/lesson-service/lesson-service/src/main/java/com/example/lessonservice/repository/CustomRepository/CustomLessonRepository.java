package com.example.lessonservice.repository.CustomRepository;

import com.example.lessonservice.commands.Lesson.LessonSearch;
import com.example.lessonservice.entities.Lesson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomLessonRepository {
    private final EntityManager em;

    public List<Lesson> search(LessonSearch searchParams) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Lesson.class);

        var root = query.from(Lesson.class);

        List<Predicate> predicates = new ArrayList<>();


        if (searchParams.getId() != null) {
            predicates.add(cb.equal(root.get("id"), searchParams.getId()));
        }

        if (searchParams.getTitle() != null) {
            predicates.add(cb.equal(root.get("title"), searchParams.getTitle()));
        }
        if (searchParams.getDescription() != null) {
            predicates.add(cb.equal(root.get("description"), searchParams.getDescription()));
        }

        if (searchParams.getContentList() != null) {
            predicates.add(cb.equal(root.get("contentList"), searchParams.getContentList()));
        }

        if (searchParams.getAttachments() != null) {
            predicates.add(cb.equal(root.get("attachments"), searchParams.getAttachments()));
        }
        if (searchParams.getCourseId()!= null) {
            predicates.add(cb.equal(root.get("courseId"), searchParams.getCourseId()));
        }
        if (searchParams.getCreatedAt()!= null) {
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
