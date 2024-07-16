package com.example.lessonservice.commands.Attachment.response;

import com.example.lessonservice.entities.Lesson;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponse {
    private Long id;

    private Lesson lesson;

    private String fileName;

    private String url;

    private String fileType;

    private Date createdAt;

}
