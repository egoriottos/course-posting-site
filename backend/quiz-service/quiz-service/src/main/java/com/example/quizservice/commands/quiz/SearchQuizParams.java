package com.example.quizservice.commands.quiz;

import com.example.quizservice.domain.entity.Question;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchQuizParams {
    public SearchQuizParams(@JsonProperty("id")Long id,@JsonProperty("title") String title,
                            @JsonProperty("lessonId") Long lessonId,@JsonProperty("description") String description,
                            @JsonProperty("questions") List<Question> questions,
                            @JsonProperty("createdAt") Date createdAt,@JsonProperty("updatedAt") Date updatedAt,
                            @JsonProperty("limit") Integer limit,@JsonProperty("offset") Integer offset) {
        this.id = id;
        this.title = title;
        this.lessonId = lessonId;
        this.description = description;
        this.questions = questions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.limit = 15;
        this.offset = 0;
    }

    private Long id;
    private String title;
    private Long lessonId;
    private String description;
    private List<Question> questions;
    private Date createdAt;
    private Date updatedAt;
    private Integer limit;
    private Integer offset;
}
