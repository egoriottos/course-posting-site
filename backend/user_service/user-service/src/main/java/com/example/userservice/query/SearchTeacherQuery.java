package com.example.userservice.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchTeacherQuery {
    public SearchTeacherQuery(@JsonProperty("id") Long id, @JsonProperty("ownerId") Long ownerId,
                              @JsonProperty("coursesId") List<Integer> coursesId, @JsonProperty("rating") Float rating,
                              @JsonProperty("limit") Integer limit, @JsonProperty("offset") Integer offset) {
        this.id = id;
        this.ownerId = ownerId;
        this.coursesId = coursesId;
        this.rating = rating;
        this.limit = limit;
        this.offset = offset;
    }

    private Long id;
    private Long ownerId;
    private List<Integer> coursesId;
    private Float rating;
    private Integer limit;
    private Integer offset;
}
