package com.example.courseservice.infrostructure.config.repositories;

import com.example.courseservice.domain.entities.Course;
import com.example.courseservice.domain.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findModulesByCourseId(Long courseId);
    Optional<Integer> findMaxOrderByCourse(Course course);
    List<Module> findByCourseAndSerialNumberGreaterThan(Course course, int number);

    List<Module> findByCourseAndSerialNumberBetween(Course course, int start, int end);

    Integer countByCourse(Course course);

    void deleteModulesByCourse(Course course);
}
