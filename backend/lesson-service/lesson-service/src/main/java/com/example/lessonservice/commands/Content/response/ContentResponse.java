package com.example.lessonservice.commands.Content.response;

import com.example.lessonservice.entities.Lesson;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentResponse {

    private Long id;

    private Lesson lesson;

    private String data;

    private String url;

    private Date createdAt;

    private Date updatedAt;
}
