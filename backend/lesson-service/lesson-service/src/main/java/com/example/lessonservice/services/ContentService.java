package com.example.lessonservice.services;

import com.example.lessonservice.commands.Content.ContentSearch;
import com.example.lessonservice.commands.Content.CreateContentCommand;
import com.example.lessonservice.commands.Content.UpdateContentCommand;
import com.example.lessonservice.commands.Content.response.ContentResponse;
import com.example.lessonservice.entities.Content;
import com.example.lessonservice.repository.ContentRepository;
import com.example.lessonservice.repository.CustomRepository.CustomContentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;
    private final CustomContentRepository contentCustomRepository;
    private final ModelMapper modelMapper;
    @Transactional
    public void create(CreateContentCommand createContentCommand) {
        var content = Content.builder()
                .url(createContentCommand.getUrl())
                .data(createContentCommand.getData())
                .lesson(createContentCommand.getLesson())
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
        contentRepository.save(content);
    }

    public List<ContentResponse> search(ContentSearch search){
        List<Content> contents = contentCustomRepository.search(search);
        return contents.stream().map(obj->modelMapper.map(obj, ContentResponse.class)).toList();
    }
    @Transactional
    public void updateContentById(Long id, UpdateContentCommand content) {
        var contentToUpdate = contentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!contentToUpdate.getLesson().equals(content.getLesson())
                || !contentToUpdate.getData().equals(content.getData())
                || !contentToUpdate.getUrl().equals(content.getUrl()))
        {
            contentToUpdate.setLesson(content.getLesson());
            contentToUpdate.setData(content.getData());
            contentToUpdate.setUrl(content.getUrl());
            contentToUpdate.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            contentRepository.save(contentToUpdate);
        }
        else {
            throw new RuntimeException("Content cannot be updated because it is empty");
        }
    }
    @Transactional
    public void deleteContentById(Long id) {
        contentRepository.deleteById(id);
    }

}
