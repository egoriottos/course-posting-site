package com.example.userprogressservice.searchParams;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchLessonProgressParam {
    public SearchLessonProgressParam(@JsonProperty("id") Long id,@JsonProperty("userId") Long userId,
                                     @JsonProperty("courseId") Long courseId,@JsonProperty("lessonId") Long lessonId,
                                     @JsonProperty("completed") Boolean completed,@JsonProperty("completionDate") Date completionDate,
                                     @JsonProperty("createdAt") Date createdAt,@JsonProperty("updatedAt") Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.lessonId = lessonId;
        this.completed = completed;
        this.completionDate = completionDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.limit = 15;
        this.offset = 0;
    }

    private Long id;
    private Long userId;
    private Long courseId;
    private Long lessonId;
    private Boolean completed;
    private Date completionDate;
    private Date createdAt;
    private Date updatedAt;
    private Integer limit;
    private Integer offset;
}
