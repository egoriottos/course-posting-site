package com.example.lessonservice.commands.Content.contentQuery;

import com.example.lessonservice.entities.Lesson;
import com.example.lessonservice.enums.ContentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentQuery {
    private Lesson lesson;

    private ContentType type;

    private String data;

    private String url;
}
