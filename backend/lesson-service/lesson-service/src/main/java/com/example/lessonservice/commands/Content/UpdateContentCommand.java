package com.example.lessonservice.commands.Content;

import com.example.lessonservice.entities.Lesson;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContentCommand {
    private Lesson lesson;

    private String data;

    private String url;
}
