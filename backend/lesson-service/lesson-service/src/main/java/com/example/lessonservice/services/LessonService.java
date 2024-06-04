package com.example.lessonservice.services;

import com.example.lessonservice.entities.Lesson;
import com.example.lessonservice.repository.LessonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;


    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLesson(long id) {
        return lessonRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Lesson not found"));
    }

    public Lesson updateLesson(long id, Lesson lesson) {
        var lessonToUpdate = getLesson(id);
        if(!lessonToUpdate.getTitle().equals(lesson.getTitle())
                || !lessonToUpdate.getDescription().equals(lesson.getDescription())
                || !lessonToUpdate.getContentList().equals(lesson.getContentList())
                || !lessonToUpdate.getAttachments().equals(lesson.getAttachments())
                || !lessonToUpdate.getCourseId().equals(lesson.getCourseId())
                || lessonToUpdate.isPublished() != lesson.isPublished()){
            lesson = lessonRepository.save(lessonToUpdate);
        }
        return lesson;
    }

    public void deleteLesson(long id) {
        lessonRepository.deleteById(id);
    }

    public List<Lesson> getLessonsByCourseId(long courseId) {
        try {
            return lessonRepository.findByCourseId(courseId);
        }
        catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Lesson not found");
        }
    }
    public List<Lesson> getLessonByTitle(String title) {
        List<Lesson> lessons = getAllLessons();
        List<Lesson> findedLessons = new ArrayList<>();
        for(Lesson lesson : lessons) {
            if(lesson.getTitle().equals(title) || lesson.getTitle().contains(title)) {
                findedLessons.add(lesson);
            }
            else {
                throw new EntityNotFoundException("Lesson not found");
            }
        }
        return findedLessons;
    }
}
