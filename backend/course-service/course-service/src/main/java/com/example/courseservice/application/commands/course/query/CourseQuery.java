package com.example.courseservice.application.commands.course.query;

import com.example.courseservice.domain.entities.Module;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseQuery {
    public CourseQuery(@JsonProperty("id")Long id,@JsonProperty("title") String title,
                       @JsonProperty("description")String description,@JsonProperty("author") String author,
                       @JsonProperty("modules")List<Module> modules,
                       @JsonProperty("createdAt")Date createdAt,
                       @JsonProperty("updatedAt")Date updatedAt,
                       @JsonProperty("limit")Integer limit,
                       @JsonProperty("offset")Integer offset) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.modules = modules;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.limit = limit;
        this.offset = offset;
    }

    private Long id;
    private String title;
    private String description;
    private String author;
    private List<Module> modules;
    private Date createdAt;
    private Date updatedAt;
    private Integer limit;
    private Integer offset;
}
