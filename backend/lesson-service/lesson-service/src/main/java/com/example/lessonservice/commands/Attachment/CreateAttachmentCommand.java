package com.example.lessonservice.commands.Attachment;

import com.example.lessonservice.entities.Lesson;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAttachmentCommand {
    private Lesson lesson;

    private String fileName;

    private String fileType;
}
