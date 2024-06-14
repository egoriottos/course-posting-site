package com.example.userservice.repository;

import com.example.userservice.domain.entity.Student;
import com.example.userservice.query.SearchStudentQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomStudentRepository {
     private final EntityManager em;
//     private Long id;
//     private Long ownerId;
//     private List<Integer> quizResultsId;
//     private List<Integer> coursesId;

     public List<Student> search(SearchStudentQuery params) {
          var cb = em.getCriteriaBuilder();
          var query = cb.createQuery(Student.class);

          var root = query.from(Student.class);

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
          if (params.getQuizResultsId() != null) {
               predicates.add(
                       cb.equal(root.get("quizResult").get("id"),params.getQuizResultsId()));
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
