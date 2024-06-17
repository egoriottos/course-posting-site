package com.example.userservice.repository;

import com.example.userservice.domain.entity.User;
import com.example.userservice.query.SearchParams;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomUserRepository {
    private final EntityManager em;

    public List<User> search(SearchParams searchParams) {
        var cb = em.getCriteriaBuilder();
        var query = cb.createQuery(User.class);

        var root = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();


        if (searchParams.getId() != null) {
            predicates.add(cb.equal(root.get("id"), searchParams.getId()));
        }

        if (searchParams.getLogin() != null) {
            predicates.add(cb.equal(root.get("login"), searchParams.getLogin()));
        }
        if (searchParams.getEmail() != null) {
            predicates.add(cb.equal(root.get("email"), searchParams.getEmail()));
        }

        if (searchParams.getFirstname() != null) {
            predicates.add(cb.equal(root.get("firstname"), searchParams.getFirstname()));
        }

        if (searchParams.getLastname() != null) {
            predicates.add(cb.equal(root.get("lastname"), searchParams.getLastname()));
        }
        if (searchParams.getPhone()!= null) {
            predicates.add(cb.equal(root.get("phone"), searchParams.getPhone()));
        }
        if (searchParams.getCreatedAt() != null) {
            predicates.add(cb.equal(root.get("createdAt"), searchParams.getCreatedAt()));
        }
        if (searchParams.getDeletedAt() != null) {
            predicates.add(cb.equal(root.get("deletedAt"), searchParams.getDeletedAt()));
        }
        if (searchParams.getDateOfBirth() != null) {
            predicates.add(cb.equal(root.get("dateOfBirth"), searchParams.getDateOfBirth()));
        }
        if(searchParams.getRoles() !=null){
            predicates.add(cb.equal(root.get("roles"), searchParams.getRoles()));
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
