package com.example.lessonservice.commands.Lesson.lessonQuery;

import com.example.lessonservice.entities.Attachment;
import com.example.lessonservice.entities.Content;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonQuery {

    private String title;

    private String description;

    private List<Content> contentList;

    private List<Attachment> attachments;

    private Long courseId;

    private boolean published;
}
