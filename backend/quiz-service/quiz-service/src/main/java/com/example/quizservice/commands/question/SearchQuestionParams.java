package com.example.quizservice.commands.question;

import com.example.quizservice.domain.entity.Image;
import com.example.quizservice.domain.enums.QuestionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchQuestionParams {
    public SearchQuestionParams(@JsonProperty("id") Long id,@JsonProperty("text") String text,
                                @JsonProperty("type") QuestionType type,@JsonProperty("options") List<String> options,
                                @JsonProperty("correctAnswers") List<String> correctAnswers,@JsonProperty("images") List<Image> images,
                                @JsonProperty("createdAt") Date createdAt,@JsonProperty("updatedAt") Date updatedAt,
                                @JsonProperty("limit") Integer limit,@JsonProperty("offset") Integer offset) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.options = options;
        this.correctAnswers = correctAnswers;
        this.images = images;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.limit = 15;
        this.offset = 0;
    }

    private Long id;

    private String text;

    private QuestionType type;

    private List<String> options; //вариантов ответа

    private List<String> correctAnswers; // правильные ответы

    private List<Image> images;// список изображений для вопроса

    private Date createdAt;

    private Date updatedAt;

    private Integer limit;

    private Integer offset;
}
