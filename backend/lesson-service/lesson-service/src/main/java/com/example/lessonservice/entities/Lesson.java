package com.example.lessonservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "lessons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Content> contentList;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    private Long courseId;

    private boolean published;

}
