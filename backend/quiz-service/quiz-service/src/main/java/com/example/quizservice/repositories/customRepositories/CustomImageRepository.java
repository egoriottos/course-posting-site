package com.example.quizservice.repositories.customRepositories;

import com.example.quizservice.commands.image.SearchImageParams;
import com.example.quizservice.domain.entity.Image;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomImageRepository {
//    private Long id;
//    private String url;
//    private Integer limit;
//    private Integer offset;
    private final EntityManager em;
    public List<Image> search(SearchImageParams params) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(Image.class);

        var root = query.from(Image.class);

        List<Predicate> predicates = new ArrayList<>();

        if (params.getId() != null) {
            predicates.add(
                    cb.equal(root.get("id"), params.getId())
            );
        }
        if (params.getUrl() != null) {
            predicates.add(
                    cb.equal(root.get("url"), params.getUrl())
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
