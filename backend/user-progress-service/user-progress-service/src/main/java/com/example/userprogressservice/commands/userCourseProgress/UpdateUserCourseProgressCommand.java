package com.example.userprogressservice.commands.userCourseProgress;

import lombok.Data;

@Data
public class UpdateUserCourseProgressCommand {
    private Long userId;
    private Long courseId;
    private Double progressPercentage;
    private Boolean completed;
}
