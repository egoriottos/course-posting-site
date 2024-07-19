package com.example.userprogressservice.dto;

import lombok.Data;

@Data
public class UserCourseProgressDto {
    private Long id;
    private Long userId;
    private Long courseId;
    private Double progressPercentage;
    private Boolean completed;
}
