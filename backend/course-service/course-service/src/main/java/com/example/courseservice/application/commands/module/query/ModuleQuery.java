package com.example.courseservice.application.commands.module.query;

import com.example.courseservice.domain.entities.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuleQuery {
    public ModuleQuery(@JsonProperty("id")Long id, @JsonProperty("title")String title,
                       @JsonProperty("description")String description, @JsonProperty("order")Integer order,
                       @JsonProperty("course")Course course, @JsonProperty("lessonId")List<Integer> lessonsId,
                       @JsonProperty("quizId")Integer quizId, @JsonProperty("createdAt")Date createdAt,
                       @JsonProperty("updatedAt")Date updatedAt, @JsonProperty("limit")Integer limit,
                       @JsonProperty("offset")Integer offset) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.order = order;
        this.course = course;
        this.lessonsId = lessonsId;
        this.quizId = quizId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.limit = limit;
        this.offset = offset;
    }

    private Long id;
    private String title;
    private String description;
    private Integer order;
    private Course course;
    private List<Integer> lessonsId;
    private Integer quizId;
    private Date createdAt;
    private Date updatedAt;
    private Integer limit;
    private Integer offset;
}
