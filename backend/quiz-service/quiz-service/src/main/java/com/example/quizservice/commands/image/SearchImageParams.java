package com.example.quizservice.commands.image;

import com.example.quizservice.domain.entity.Question;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchImageParams {
    public SearchImageParams(@JsonProperty("id") Long id,@JsonProperty("url") String url,
                             @JsonProperty("question") Question question,
                             @JsonProperty("limit") Integer limit,@JsonProperty("offset") Integer offset) {
        this.id = id;
        this.url = url;
        this.question = question;
        this.limit = 15;
        this.offset = 0;
    }

    private Long id;
    private String url;
    private Question question;
    private Integer limit;
    private Integer offset;
}
