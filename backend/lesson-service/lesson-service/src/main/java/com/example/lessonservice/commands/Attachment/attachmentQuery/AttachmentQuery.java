package com.example.lessonservice.commands.Attachment.attachmentQuery;

import com.example.lessonservice.entities.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentQuery {
    private Lesson lesson;

    private String fileName;

    private String url;

    private String fileType;
}
