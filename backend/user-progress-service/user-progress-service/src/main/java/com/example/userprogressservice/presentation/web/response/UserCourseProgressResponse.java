package com.example.userprogressservice.presentation.web.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserCourseProgressResponse {
    private Long id;
    private Long userId;
    private Long courseId;
    private Double progressPercentage;
    private Boolean completed;
    private Date createdAt;
    private Date updatedAt;
    private List<Long> completedLessons = new ArrayList<>();
    private List<Long> completedTests = new ArrayList<>();
}
