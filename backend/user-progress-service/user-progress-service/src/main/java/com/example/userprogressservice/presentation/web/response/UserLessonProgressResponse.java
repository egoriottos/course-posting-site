package com.example.userprogressservice.presentation.web.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserLessonProgressResponse {
    private Long id;
    private Long userId;
    private Long courseId;
    private Long lessonId;
    private Boolean completed;
    private Date completionDate;
    private Date createdAt;
    private Date updatedAt;
}
