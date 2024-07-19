package com.example.userprogressservice.searchParams;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchCourseProgressParam {
    public SearchCourseProgressParam(@JsonProperty("id") Long id,@JsonProperty("userId") Long userId,
                                     @JsonProperty("courseId") Long courseId,@JsonProperty("progressPercentage") Double progressPercentage,
                                     @JsonProperty("completed") Boolean completed,@JsonProperty("createdAt") Date createdAt,
                                     @JsonProperty("updatedAt") Date updatedAt,@JsonProperty("completedLessons") List<Long> completedLessons,
                                     @JsonProperty("completedTests") List<Long> completedTests) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.progressPercentage = progressPercentage;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.completedLessons = completedLessons;
        this.completedTests = completedTests;
        this.limit = 15;
        this.offset = 0;
    }

    private Long id;
    private Long userId;
    private Long courseId;
    private Double progressPercentage;
    private Boolean completed;
    private Date createdAt;
    private Date updatedAt;
    private List<Long> completedLessons;
    private List<Long> completedTests;
    private Integer limit;
    private Integer offset;
}
