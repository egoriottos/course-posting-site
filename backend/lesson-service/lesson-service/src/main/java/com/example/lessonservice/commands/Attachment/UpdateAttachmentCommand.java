package com.example.lessonservice.commands.Attachment;

import com.example.lessonservice.entities.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAttachmentCommand {
    private Lesson lesson;

    private String fileName;

    private String url;

    private String fileType;
}
