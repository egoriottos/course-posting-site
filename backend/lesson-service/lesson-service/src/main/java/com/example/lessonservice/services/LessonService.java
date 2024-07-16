package com.example.lessonservice.services;

import com.example.lessonservice.commands.Lesson.CreateLessonCommand;
import com.example.lessonservice.commands.Lesson.LessonSearch;
import com.example.lessonservice.commands.Lesson.UpdateLessonCommand;
import com.example.lessonservice.commands.Lesson.response.LessonResponse;
import com.example.lessonservice.entities.Lesson;
import com.example.lessonservice.repository.CustomRepository.CustomLessonRepository;
import com.example.lessonservice.repository.LessonRepository;
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
public class LessonService {
    private final LessonRepository lessonRepository;
    private final CustomLessonRepository customLessonRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public LessonResponse createLesson(CreateLessonCommand createLessonCommand) {
        var lesson = Lesson.builder()
                .attachments(createLessonCommand.getAttachments())
                .contentList(createLessonCommand.getContentList())
                .description(createLessonCommand.getDescription())
                .title(createLessonCommand.getTitle())
                .courseId(createLessonCommand.getCourseId())
                .published(createLessonCommand.isPublished())
                .createdAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
         return modelMapper.map(lessonRepository.save(lesson),LessonResponse.class);
    }
    @Transactional
    public LessonResponse updateLesson(long id, UpdateLessonCommand lesson) {
        var lessonToUpdate = lessonRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!lessonToUpdate.getTitle().equals(lesson.getTitle())
                || !lessonToUpdate.getDescription().equals(lesson.getDescription())
                || !lessonToUpdate.getContentList().equals(lesson.getContentList())
                || !lessonToUpdate.getAttachments().equals(lesson.getAttachments())
                || !lessonToUpdate.getCourseId().equals(lesson.getCourseId())
                || lessonToUpdate.isPublished() != lesson.isPublished())
        {
            lessonToUpdate.setTitle(lesson.getTitle());
            lessonToUpdate.setDescription(lesson.getDescription());
            lessonToUpdate.setContentList(lesson.getContentList());
            lessonToUpdate.setAttachments(lesson.getAttachments());
            lessonToUpdate.setCourseId(lesson.getCourseId());
            lessonToUpdate.setPublished(lesson.isPublished());
            lessonToUpdate.setUpdatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            return modelMapper.map(lessonRepository.save(lessonToUpdate),LessonResponse.class);
        }
        else {
            throw new EntityNotFoundException("Lesson not found");
        }
    }
    @Transactional
    public void deleteLesson(long id) {
        lessonRepository.deleteById(id);
    }

    public List<LessonResponse> search(LessonSearch search){
        List<Lesson> lessons = customLessonRepository.search(search);
        return lessons.stream().map(obj->modelMapper.map(obj, LessonResponse.class)).toList();

    }
    public LessonResponse getLesson(long id) {
        return modelMapper.map(lessonRepository.findById(id),LessonResponse.class);
    }

    public List<LessonResponse> getLessonsByCourseId(long id){
        return lessonRepository.findAllByCourseId(id).stream().map(obj->modelMapper.map(obj, LessonResponse.class)).toList();
    }
}
