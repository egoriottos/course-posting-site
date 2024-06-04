package com.example.lessonservice.repository;

import com.example.lessonservice.entities.Content;
import com.example.lessonservice.entities.ContentType;
import com.example.lessonservice.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByLesson(Lesson lesson);
    List<Content> getContentsByType(ContentType type);
}
