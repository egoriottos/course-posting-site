package com.example.userprogressservice.commands.userLessonProgress;

import lombok.Data;
import java.util.Date;

@Data
public class CreateUserLessonProgressCommand {
    private Long userId;
    private Long courseId;
    private Long lessonId;
    private Boolean completed;
    private Date completionDate;
}
