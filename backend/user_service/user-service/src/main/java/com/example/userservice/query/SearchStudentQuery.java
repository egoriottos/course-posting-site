package com.example.userservice.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchStudentQuery {
    public SearchStudentQuery(@JsonProperty("id")Long id,@JsonProperty("ownerId") Long ownerId,
                              @JsonProperty("quizResultsId") List<Integer> quizResultsId,
                              @JsonProperty("coursesId") List<Integer> coursesId, @JsonProperty("limit") Integer limit,
                              Integer offset) {
        this.id = id;
        this.ownerId = ownerId;
        this.quizResultsId = quizResultsId;
        this.coursesId = coursesId;
        this.limit = limit;
        this.offset = offset;
    }

    private Long id;
    private Long ownerId;
    private List<Integer> quizResultsId;
    private List<Integer> coursesId;
    private Integer limit;
    private Integer offset;
}
