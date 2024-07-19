package com.example.userprogressservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class UserLessonProgressDto {
    private Long id;
    private Long userId;
    private Long courseId;
    private Long lessonId;
    private Boolean completed;
    @Column
    private Date completionDate;
}
