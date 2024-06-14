package com.example.courseservice.repositories;

import com.example.courseservice.entities.Course;
import com.example.courseservice.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findModulesByCourseId(Long courseId);
    Optional<Integer> findMaxOrderByCourse(Course course);
    List<Module> findByCourseAndOrderGreaterThan(Course course, int order);

    List<Module> findByCourseAndOrderBetween(Course course, int start, int end);

    Integer countByCourse(Course course);

    void deleteModulesByCourse(Course course);
}
