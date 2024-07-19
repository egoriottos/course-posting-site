package com.example.userprogressservice.searchParams;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchQuizProgressParam {
    public SearchQuizProgressParam(@JsonProperty("id") Long id,@JsonProperty("userId") Long userId,
                                   @JsonProperty("quizId") Long quizId,@JsonProperty("score") Double score,
                                   @JsonProperty("completed") Boolean completed,@JsonProperty("dateTaken") Date dateTaken,
                                   @JsonProperty("createdAt")Date createdAt,@JsonProperty("updatedAt")Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.completed = completed;
        this.dateTaken = dateTaken;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.limit = 15;
        this.offset = 0;
    }

    private Long id;
    private Long userId;
    private Long quizId;
    private Double score;
    private Boolean completed;
    private Date dateTaken;//дата когда тест был пройден
    private Date createdAt;
    private Date updatedAt;
    private Integer limit;
    private Integer offset;
}
