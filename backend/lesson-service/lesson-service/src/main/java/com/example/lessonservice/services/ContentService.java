package com.example.lessonservice.services;

import com.example.lessonservice.entities.Content;
import com.example.lessonservice.entities.ContentType;
import com.example.lessonservice.entities.Lesson;
import com.example.lessonservice.repository.ContentRepository;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;

    public Content addContent(Content content) {
        return contentRepository.save(content);
    }
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }
    public Content getContentById(Long id) {
        return contentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Content not found"));
    }
    public Content updateContentById(Long id, Content content) {
        var contentToUpdate = getContentById(id);
        if(!contentToUpdate.getLesson().equals(content.getLesson())
                || !contentToUpdate.getType().equals(content.getType())
                || !contentToUpdate.getData().equals(content.getData())
                || !contentToUpdate.getUrl().equals(content.getUrl())){
            content = contentRepository.save(content);
        }
        return content;
    }
    public void deleteContentById(Long id) {
        contentRepository.deleteById(id);
    }
    public List<Content> getContentsByLesson(Lesson lesson) {
        return contentRepository.findByLesson(lesson);
    }
    public List<Content> getContentsByType(ContentType type) {
        List<Content> contents = new ArrayList<>();
        List<Content> allContents = getAllContents();
        for (Content content : allContents) {
            if(content.getType().equals(type)){
                contents.add(content);
            }
        }
        return contents;
    }

}
